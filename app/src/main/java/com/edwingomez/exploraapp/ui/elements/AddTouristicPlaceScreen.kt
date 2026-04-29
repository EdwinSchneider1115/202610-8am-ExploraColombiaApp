package com.edwingomez.exploraapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Colores ───────────────────────────────────────────────────────────────────
private val OrangeStart      = Color(0xFFD94F2B)
private val OrangeEnd        = Color(0xFFE8722A)
private val Background       = Color(0xFFF2F1F6)
private val CardBg           = Color(0xFFFFFFFF)
private val InputBg          = Color(0xFFEEEEEE)
private val LabelColor       = Color(0xFF6B6B6B)
private val PlaceholderColor = Color(0xFFAAAAAA)

private val orangeGradient = Brush.horizontalGradient(
    colors = listOf(OrangeStart, OrangeEnd)
)

// ─────────────────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTouristicPlaceScreen(
    onBack: () -> Unit = {},
    onPublish: (name: String, department: String, city: String, description: String) -> Unit = { _, _, _, _ -> }
) {
    var placeName   by remember { mutableStateOf("") }
    var department  by remember { mutableStateOf("") }
    var city        by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Place",
                        color = OrangeStart,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás",
                            tint = OrangeStart
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background
                )
            )
        },
        // Botón siempre visible, pegado al fondo — fuera del scroll
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Background)
                    .navigationBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(brush = orangeGradient)
                ) {
                    Button(
                        onClick = { onPublish(placeName, department, city, description) },
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Publicar",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        },
        containerColor = Background
    ) { innerPadding ->

        // Contenido scrolleable que respeta el espacio del topBar y bottomBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // ── Banner ────────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(brush = orangeGradient)
                    .padding(horizontal = 28.dp, vertical = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Comparte tu\ndescubrimiento",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ayuda a otros viajeros a encontrar los tesoros\nescondidos de nuestra tierra.",
                        color = Color.White.copy(alpha = 0.88f),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }

            // ── Formulario ────────────────────────────────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardBg),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    FormField(
                        label = "NOMBRE DEL LUGAR",
                        value = placeName,
                        onValueChange = { placeName = it },
                        placeholder = "Ej: Cascada del Fin del Mundo",
                        singleLine = true
                    )
                    FormField(
                        label = "DEPARTAMENTO",
                        value = department,
                        onValueChange = { department = it },
                        placeholder = "Ej: Putumayo",
                        singleLine = true
                    )
                    FormField(
                        label = "CIUDAD",
                        value = city,
                        onValueChange = { city = it },
                        placeholder = "Ej: Mocoa",
                        singleLine = true
                    )
                    FormField(
                        label = "DESCRIPCIÓN",
                        value = description,
                        onValueChange = { description = it },
                        placeholder = "Cuéntanos por qué este lugar es especial...",
                        singleLine = false,
                        minLines = 4
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
@Composable
private fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean,
    minLines: Int = 1
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = label,
            color = LabelColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = PlaceholderColor,
                    fontSize = 14.sp
                )
            },
            singleLine = singleLine,
            minLines = if (singleLine) 1 else minLines,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor   = InputBg,
                unfocusedContainerColor = InputBg,
                disabledContainerColor  = InputBg,
                focusedIndicatorColor   = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor  = Color.Transparent,
                cursorColor             = OrangeStart,
                focusedTextColor        = Color(0xFF1A1A1A),
                unfocusedTextColor      = Color(0xFF1A1A1A)
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddTouristicPlaceScreenPreview() {
    MaterialTheme {
        AddTouristicPlaceScreen()
    }
}