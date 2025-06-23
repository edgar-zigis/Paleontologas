package com.zigis.paleontologas.features.quiz.managers

import com.android.billingclient.api.*
import com.zigis.paleontologas.core.extensions.resumeSafely
import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class PaywallManager(
    private val applicationPreferences: ApplicationPreferences,
    private val androidLifecycleProvider: AndroidLifecycleProvider
) : PurchasesUpdatedListener {

    private val productId = "unlock_leaderboard_participation"
    private var billingReady = CompletableDeferred<Unit>()

    private val billingClient: BillingClient = BillingClient.newBuilder(
        androidLifecycleProvider.getApplicationContext()
    )
        .enablePendingPurchases(
            PendingPurchasesParams.newBuilder().enableOneTimeProducts().build()
        )
        .setListener(this)
        .build()

    init {
        startBillingConnection()
    }

    fun isPremiumUser(): Boolean {
        return applicationPreferences.isPremiumUser
    }

    suspend fun launchPurchaseFlow() {
        billingReady.await()

        val productDetails = queryProductDetails() ?: return

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(
                listOf(
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .build()
                )
            )
            .build()

        androidLifecycleProvider.getActivity()?.let {
            billingClient.launchBillingFlow(it, billingFlowParams)
        }
    }

    private fun startBillingConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    billingReady.complete(Unit)
                } else {
                    billingReady.completeExceptionally(
                        IllegalStateException("Billing setup failed: ${billingResult.debugMessage}")
                    )
                }
            }

            override fun onBillingServiceDisconnected() {
                billingReady = CompletableDeferred()
            }
        })
    }

    private suspend fun queryProductDetails(): ProductDetails? = withContext(Dispatchers.IO) {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(
                listOf(
                    QueryProductDetailsParams.Product.newBuilder()
                        .setProductId(productId)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build()
                )
            ).build()

        return@withContext suspendCancellableCoroutine { continuation ->
            billingClient.queryProductDetailsAsync(params) { result, detailsList ->
                if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                    continuation.resumeSafely(detailsList.firstOrNull())
                } else {
                    continuation.resumeSafely(null)
                }
            }
        }
    }

    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                    acknowledgePurchase(purchase)
                }
            }
        }
    }

    private fun acknowledgePurchase(purchase: Purchase) {
        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient.acknowledgePurchase(params) { result ->
            if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                applicationPreferences.isPremiumUser = true
            }
        }
    }
}