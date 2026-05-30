package com.anesthesiaclinic.anesthesiabriefs.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anesthesiaclinic.anesthesiabriefs.R

@Composable
fun AppLogo(
    modifier: Modifier = Modifier,
    textSizeSp: Int = 28, // Kept for signature compatibility
    iconSizeDp: Int = 40, // Height of the logo in dp
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isNavy: Boolean = false
) {
    // Select logo based on navy/blue background requirement
    val logoRes = if (isNavy) R.drawable.logo_in_app_navy else R.drawable.logo_in_app

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = logoRes),
            contentDescription = "Anesthesia Briefs Brand Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.height(iconSizeDp.dp)
        )
    }
}
