package com.example

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
fun TestComposable(onLinkClicked: (AnnotatedString.Range<String>) -> Unit) {
    LinkText(
        modifier = Modifier,
        linkTextData = listOf(
            LinkTextData(
                tag = "not null",
                annotation = "not null",
                text = "License agreement",
                onClick = onLinkClicked,
            ),
            LinkTextData(". "),
            LinkTextData(
                text = "Please review it and give your consent by checking the checkbox",
            )
        )
    )
}

@Composable
private fun LinkText(modifier: Modifier = Modifier, linkTextData: List<LinkTextData>) {
    val annotatedString = createAnnotatedString(linkTextData)
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = { offset ->
            if (offset != 0) {
                throw IllegalStateException("Bug!")
            }
        }
    )
}

data class LinkTextData(
    val text: String,
    val tag: String? = null,
    val annotation: String? = null,
    val onClick: ((str: AnnotatedString.Range<String>) -> Unit)? = null,
)

// This is a hack to be able to make part of a string clickable, https://stackoverflow.com/a/69549929/1859486
@Composable
private fun createAnnotatedString(data: List<LinkTextData>): AnnotatedString {
    return buildAnnotatedString {
        data.forEach { linkTextData ->
            if (linkTextData.tag != null && linkTextData.annotation != null) {
                pushStringAnnotation(
                    tag = linkTextData.tag,
                    annotation = linkTextData.annotation,
                )
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                    ),
                ) {
                    append(linkTextData.text)
                }
                pop()
            } else {
                append(linkTextData.text)
            }
        }
    }
}
