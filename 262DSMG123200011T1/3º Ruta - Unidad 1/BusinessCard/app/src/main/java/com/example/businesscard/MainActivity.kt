package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFD9EBD9)
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9EBD9))
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 32.dp,
                bottom = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Sección 1: Logotipo, Nombre Y Cargo
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.android_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .background(Color(0xFF073042))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.name),
                fontSize = 40.sp,
                color = Color(0xFF202020)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.job_title),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF198754)
            )
        }

        // Sección 2:Información de contacto
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ContactRow(
                icon = Icons.Default.Phone,
                contentDescription = stringResource(R.string.phone_description),
                text = stringResource(R.string.phone)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ContactRow(
                icon = Icons.Default.Share,
                contentDescription = stringResource(R.string.share_description),
                text = stringResource(R.string.social)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ContactRow(
                icon = Icons.Default.Email,
                contentDescription = stringResource(R.string.email_description),
                text = stringResource(R.string.email)
            )
        }
    }
}

@Composable
fun ContactRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color(0xFF00875A),
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF303030)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}