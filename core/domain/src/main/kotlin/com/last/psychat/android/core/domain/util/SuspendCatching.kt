@file:OptIn(ExperimentalContracts::class)

package com.last.psychat.android.core.domain.util

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException
import kotlin.contracts.ExperimentalContracts

/**
 * runSuspendCatching
 *
 * @param block
 *
 * @return Result<T>
 *
 * 작업을 실행하고, 그 작업이 성공적으로 완료되면, 그 결과를 반환,
 * 그 작업에서 예외가 발생하면 그 예외를 캡쳐하는 역할을 수행
 */

internal inline fun <T> runSuspendCatching(block: () -> T): Result<T> {
  contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
  return runCatching(block).also { result ->
    val maybeException = result.exceptionOrNull()
    if (maybeException is CancellationException) throw maybeException
  }
}
