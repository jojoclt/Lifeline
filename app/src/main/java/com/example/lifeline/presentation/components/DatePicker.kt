package com.example.lifeline.presentation.components

import android.text.format.DateFormat
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.lifeline.R
import com.example.lifeline.presentation.task.composables.AddEditTodoEvent
import com.example.lifeline.presentation.task.composables.AddEditTodoViewModel
import java.util.Calendar
import java.util.Date

/**
 * A Jetpack Compose compatible Date Picker.
 * @author Arnau Mora, Joao Gavazzi
 * @param minDate The minimum date allowed to be picked.
 * @param maxDate The maximum date allowed to be picked.
 * @param onDateSelected Will get called when a date gets picked.
 * @param onDismissRequest Will get called when the user requests to close the dialog.
 */

private const val TAG = "DatePicker"
@Composable
fun DatePicker(
    minDate: Long? = null,
    maxDate: Long? = null,
    onDateSelected: (Date) -> Unit = {},
    onDismissRequest: () -> Unit,
    viewModel: AddEditTodoViewModel
) {

    val selDate = remember { mutableStateOf(viewModel.taskEntry.value.date) }
    Log.e(TAG, "succesfully remembered")
    // todo - add strings to resource after POC
    Dialog(onDismissRequest = { onDismissRequest() }, properties = DialogProperties()) {
        Log.e(TAG, "go to dialog")
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            Column(
                Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                // TODO: Hardcoded text
                Text(
                    text = "Select date",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = DateFormat.format("MMM d, yyyy", selDate.value).toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.size(16.dp))
            }
            Log.e(TAG, "before custom calendar view")
            CustomCalendarView(
                minDate,
                maxDate,
                onDateSelected = {
                    selDate.value = it
                }
            )
            Log.e(TAG, "out of custom calendar view function")
            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ) {
                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.textButtonColors(),
                ) {
                    //TODO - hardcode string
                    Text(
                        text = "Cancel",
                    )
                }

                Button(
                    onClick = {
                        val newDate = selDate.value
                        onDateSelected(
                            // This makes sure date is not out of range
                            Date(
                                maxOf(
                                    minOf(maxDate ?: Long.MAX_VALUE, newDate.time),
                                    minDate ?: Long.MIN_VALUE
                                )
                            )
                        )
                        viewModel.onEvent(AddEditTodoEvent.EnteredDate(newDate))
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.textButtonColors(),
                ) {
                    //TODO - hardcode string
                    Text(
                        text = "OK",
                    )
                }
                Log.e(TAG, "inside row scope")

            }
            Log.e(TAG, "outside row scopr")
        }
    }
}

/**
 * Used at [DatePicker] to create the calendar picker.
 * @author Arnau Mora, Joao Gavazzi
 * @param minDate The minimum date allowed to be picked.
 * @param maxDate The maximum date allowed to be picked.
 * @param onDateSelected Will get called when a date is selected.
 */
@Composable
private fun CustomCalendarView(
    minDate: Long? = null,
    maxDate: Long? = null,
    onDateSelected: (Date) -> Unit
) {
    // Adds view to Compose
    Log.e(TAG, "Inside custom calendar view")
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            CalendarView(ContextThemeWrapper(context, R.style.Theme_Lifeline))
        },
        update = { view ->
            if (minDate != null)
                view.minDate = minDate
            if (maxDate != null)
                view.maxDate = maxDate

            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    Calendar
                        .getInstance()
                        .apply {
                            set(year, month, dayOfMonth)
                        }
                        .time
                )
            }
        }
    )
    Log.e(TAG, "after android view")
}