package com.last.psychat.android.core.data.util.extension

import com.last.psychat.android.core.data.util.ExceptionWrapper
import java.net.UnknownHostException
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

// TODO inline keyword 를 붙히면 좋은 이유 정리
@Suppress("TooGenericExceptionCaught")
internal suspend fun <T> safeRequest(request: suspend () -> Response<T>): T? {
  try {
    val response = request()
    if (response.isSuccessful) {
      return response.body()
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
    Timber.e(exception)
    throw ExceptionWrapper(
      statusCode = exception.code(),
      message = exception.response()?.errorBody()?.string() ?: exception.message(),
      cause = exception,
    )
  } catch (exception: UnknownHostException) {
    Timber.e(exception)
    throw ExceptionWrapper(message = exception.toAlertMessage(), cause = exception)
  } catch (exception: Exception) {
    Timber.e(exception)
    throw ExceptionWrapper(message = exception.toAlertMessage(), cause = exception)
  }
}
