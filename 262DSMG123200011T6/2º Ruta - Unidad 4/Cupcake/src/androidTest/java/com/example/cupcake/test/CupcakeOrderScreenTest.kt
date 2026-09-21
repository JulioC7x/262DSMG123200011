package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // Then all the options are displayed on the screen.
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.order_cupcakes)
        ).assertIsDisplayed()

        DataSource.quantityOptions.forEach { item ->
            composeTestRule.onNodeWithText(
                composeTestRule.activity.getString(item.first)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun orderSummaryScreen_verifyContent() {
        val orderUiState = OrderUiState(
            quantity = 6,
            flavor = "Chocolate",
            date = "Tomorrow",
            price = "$100"
        )

        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = orderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> }
            )
        }

        val resources = composeTestRule.activity.resources

        val numberOfCupcakes = resources.getQuantityString(
            R.plurals.cupcakes,
            orderUiState.quantity,
            orderUiState.quantity
        )

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.quantity)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(numberOfCupcakes).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.flavor)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(orderUiState.flavor).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.pickup_date)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(orderUiState.date).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                orderUiState.price
            )
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.send).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsDisplayed()
    }

    @Test
    fun selectOptionScreen_selectOption_nextButtonEnabled() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(
                subtotal = subtotal,
                options = flavors
            )
        }

        // Initially Next is disabled.
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()

        // Select a flavor.
        composeTestRule.onNodeWithText("Chocolate").performClick()

        // Now Next is enabled.
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}