package com.last.psychat.android.core.data.datasource.remote.chat

import com.last.psychat.android.core.data.model.chat.ChatRequest
import com.last.psychat.android.core.data.model.chat.ChatResponse
import com.last.psychat.android.core.data.model.chat.CheckEmotionIsJudgeResponse
import com.last.psychat.android.core.data.model.chat.EmotionResponse
import com.last.psychat.android.core.data.model.chat.EndChatRequest
import com.last.psychat.android.core.data.model.chat.PreviousChatDetailResponse
import com.last.psychat.android.core.data.model.chat.PreviousChatListResponse
import com.last.psychat.android.core.data.model.chat.SessionResponse
import com.last.psychat.android.core.data.service.ChatService
import com.last.psychat.android.core.data.util.ExceptionWrapper
import com.last.psychat.android.core.data.util.extension.safeRequest
import com.last.psychat.android.core.data.util.extension.toAlertMessage
import com.last.psychat.android.core.domain.util.EndChatSessionResponseServerError
import java.net.UnknownHostException
import javax.inject.Inject
import retrofit2.HttpException
import timber.log.Timber

internal class ChatRemoteDataSourceImpl @Inject constructor(
  private val service: ChatService,
) : ChatRemoteDataSource {
  override suspend fun getPreviousChatList(): PreviousChatListResponse? {
    return safeRequest {
      service.getPreviousChatList()
    }
  }

  override suspend fun getPreviousChatDetail(sessionId: Long): PreviousChatDetailResponse? {
    return safeRequest {
      service.getPreviousChatDetail(sessionId)
    }
  }

  override suspend fun startChatSession(): SessionResponse? {
    return safeRequest {
      service.startChatSession()
    }
  }

  override suspend fun sendChatMessage(chatRequest: ChatRequest): ChatResponse? {
    return safeRequest {
      service.sendChatMessage(chatRequest)
    }
  }

  override suspend fun checkEmotionIsJudged(endChatRequest: EndChatRequest): CheckEmotionIsJudgeResponse? {
    return safeRequest {
      service.checkEmotionIsJudged(endChatRequest)
    }
  }

  override suspend fun endChatSession(endChatRequest: EndChatRequest): EmotionResponse? {
//    return safeRequest {
//      service.endChatSession(endChatRequest)
//    }
    try {
      val response = service.endChatSession(endChatRequest)
      if (response.isSuccessful) {
        return response.body()
      } else if (response.code() == 500) {
        throw EndChatSessionResponseServerError
      } else {
        val errorBody = response.errorBody()?.string() ?: "Unknown error"
        Timber.d(Exception(errorBody))
        throw ExceptionWrapper(
          statusCode = response.code(),
          message = Exception(errorBody).toAlertMessage(),
          cause = Exception(errorBody),
        )
      }
    } catch (exception: HttpException) {
      Timber.d(exception)
      throw ExceptionWrapper(
        statusCode = exception.code(),
        message = exception.response()?.errorBody()?.string() ?: exception.message(),
        cause = exception,
      )
    } catch (exception: UnknownHostException) {
      Timber.d(exception)
      throw ExceptionWrapper(
        message = exception.toAlertMessage(),
        cause = exception,
      )
    } catch (exception: Exception) {
      Timber.d(exception)
      throw ExceptionWrapper(
        message = exception.toAlertMessage(),
        cause = exception,
      )
    }
  }
}
