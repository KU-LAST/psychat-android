package com.last.psychat.android.core.domain.usecase.recommend

import com.last.psychat.android.core.domain.entity.recommend.EmotionRequestEntity
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentListEntity
import com.last.psychat.android.core.domain.repository.RecommendRepository
import com.last.psychat.android.core.domain.util.GetRecommendedContentListResponseIsNull
import com.last.psychat.android.core.domain.util.runSuspendCatching
import javax.inject.Inject

class GetRecommendedContentListUseCase @Inject constructor(
  private val repository: RecommendRepository,
) {
  suspend operator fun invoke(emotionRequestEntity: EmotionRequestEntity): Result<RecommendedContentListEntity> {
    return runSuspendCatching {
      repository.getRecommendedContentList(emotionRequestEntity) ?: throw GetRecommendedContentListResponseIsNull
    }
  }
}
