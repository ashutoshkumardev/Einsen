package dev.spikeysanju.einsen.utils.viewstate

import dev.spikeysanju.einsen.model.emoji.EmojiItem

sealed class EmojiViewState {
    // Represents different states for the All Emoji
    object Empty : EmojiViewState()
    object Loading : EmojiViewState()
    data class Success(val emojiItem: List<EmojiItem>) : EmojiViewState()
    data class Error(val exception: Throwable) : EmojiViewState()
}