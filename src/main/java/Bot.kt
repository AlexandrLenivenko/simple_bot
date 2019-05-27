import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

class Bot: TelegramLongPollingBot() {


    override fun getBotUsername(): String = "ASLenivenkoTestBot"

    override fun getBotToken(): String = "838246673:AAHl1w8BCa0Pt82al_IwuvT9UjdLFiUBgBY"

    override fun onUpdateReceived(update: Update?) {
        update?.let {
            if(!it.message.hasText()) return

            when(it.message.text) {
                "/help" -> sendNewMessage(it.message, "What would you like to do")
                "/t" -> sendNewMessage(it.message, "Currently, it is 18 degrees")
                else -> ""
            }
        }
    }

    private fun sendNewMessage(message: Message, test: String) {
        val newMessage = SendMessage().apply {
            enableMarkdown(true)
            chatId = message.chatId.toString()
            replyToMessageId = message.messageId
            text = test
        }

        execute(newMessage)
    }

}

fun main() {
    ApiContextInitializer.init()
    val telegramBotApi = TelegramBotsApi()

    telegramBotApi.registerBot(Bot())

}
