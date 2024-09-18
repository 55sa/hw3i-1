package com.example.hw3i_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw3i_1.ui.theme.Hw3i1Theme

class ShoppingItem {


    var name: String
    var quantity: Int

   
    constructor(name: String, quantity: Int) {
        this.name = name
        this.quantity = quantity
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw3i1Theme {
               shop()
            }
        }
    }
}
@Composable
fun ShoppingListItem(item: ShoppingItem) {
    var check by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = check,
            onCheckedChange = {check=it}
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("${item.name} - ${item.quantity}")
    }
}

@Composable
fun shop(){

    var input by remember { mutableStateOf("") }
    var cart = remember { mutableStateListOf<String>() }

    val checkedState = remember { mutableStateMapOf<String, Boolean>() }

    var products = remember { mutableStateListOf<ShoppingItem>(
         ShoppingItem("Dior",10),
        ShoppingItem("Volvo",3),
         ShoppingItem("Banana",5),
          ShoppingItem("Apple",99),
        ShoppingItem("Peach",6),

    ) }
    Column {

        Button(onClick = {
            products.forEach { product ->
                if (checkedState[product.name] == true) {
                    if (!cart.contains(product.name)) {
                        cart.add(product.name) // Add checked products to the cart
                    }
                } else {
                    cart.remove(product.name) // Remove unchecked products from the cart
                }
            }


        }) {Text("Add to cart") }
        Spacer(modifier = Modifier.size(10.dp))

        LazyColumn {
            items(products.toList()){
                item->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedState[item.name] ?: false,
                        onCheckedChange = {
                            checkedState[item.name] = it
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${item.name} - ${item.quantity}")

                }
            }

        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(text = "Cart: ${cart.joinToString(", ")}", modifier = Modifier.padding(16.dp))


    }




}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hw3i1Theme {
        Greeting("Android")
    }
}