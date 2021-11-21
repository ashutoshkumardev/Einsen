/*
 *
 *  * Copyright 2021 Spikey Sanju
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package dev.spikeysanju.einsen.view.project

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import dev.spikeysanju.einsen.R
import dev.spikeysanju.einsen.components.ItemWorkspaceCard
import dev.spikeysanju.einsen.navigation.MainActions
import dev.spikeysanju.einsen.ui.theme.einsenColors
import dev.spikeysanju.einsen.ui.theme.typography
import dev.spikeysanju.einsen.view.viewmodel.MainViewModel

@Composable
fun ProjectScreen(
    modifier: Modifier,
    viewModel: MainViewModel,
    actions: MainActions,
    toggleTheme: () -> Unit
) {

    val gridSize by remember { mutableStateOf(150.dp) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.text_project),
                        textAlign = TextAlign.Start,
                        style = typography.h5,
                        color = einsenColors.black
                    )
                },
                actions = {

                    // log event to firebase
                    val bundle = bundleOf(
                        "theme_switch" to isSystemInDarkTheme()
                    )

                    IconButton(
                        onClick = {
                            toggleTheme().run {
                                viewModel.firebaseLogEvent("project_theme_switch", bundle)
                            }
                        }
                    ) {

                        Icon(
                            painter = when (isSystemInDarkTheme()) {
                                true -> painterResource(id = R.drawable.ic_bulb_on)
                                false -> painterResource(id = R.drawable.ic_bulb_off)
                            },
                            contentDescription = stringResource(R.string.text_bulb_turn_on),
                            tint = einsenColors.black
                        )
                    }

                    Spacer(modifier = modifier.width(8.dp))

                    IconButton(
                        onClick = {
                            actions.gotoAbout.invoke().run {
                                // log event to firebase
                                val aboutBundle = bundleOf(
                                    "about_button" to "Clicked about button from Project"
                                )
                                viewModel.firebaseLogEvent("project_about_button", aboutBundle)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_about),
                            contentDescription = stringResource(R.string.text_bulb_turn_on),
                            tint = einsenColors.black
                        )
                    }
                },
                backgroundColor = einsenColors.background, elevation = 0.dp
            )
        },
        floatingActionButton = {

            FloatingActionButton(
                modifier = modifier.padding(30.dp),
                onClick = {
                    actions.gotoAddTask.invoke(0, 0).run {
                        // log event to firebase
                        val addTaskBundle = bundleOf(
                            "add_project" to "Clicked Add Project button from Workspace"
                        )

                        viewModel.firebaseLogEvent("project_add_workspace_button", addTaskBundle)
                    }
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.background,
                elevation = FloatingActionButtonDefaults.elevation(12.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.text_addTask),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        }
    ) {

        // workspace dashboard layout goes here
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = gridSize)
        ) {

            item {
                ItemWorkspaceCard(
                    modifier = modifier,
                    title = "Einsen app",
                    itemCount = "4 action items",
                    emoji = "\uD83D\uDD25",
                    tag = "Open source",
                    onCardClick = {}
                ) {
                }
            }

            item {
                ItemWorkspaceCard(
                    modifier = modifier,
                    title = "Einsen app",
                    itemCount = "4 action items",
                    emoji = "\uD83D\uDD25",
                    tag = "Open source",
                    onCardClick = {}
                ) {
                }
            }

            item {
                ItemWorkspaceCard(
                    modifier = modifier,
                    title = "Einsen app",
                    itemCount = "4 action items",
                    emoji = "\uD83D\uDD25",
                    tag = "Open source",
                    onCardClick = {}
                ) {
                }
            }
        }
    }
}
