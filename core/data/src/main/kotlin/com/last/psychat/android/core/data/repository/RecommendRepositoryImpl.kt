package com.last.psychat.android.core.data.repository

import com.last.psychat.android.core.data.datasource.remote.recommend.RecommendDataSource
import com.last.psychat.android.core.data.mapper.recommend.toEntity
import com.last.psychat.android.core.data.mapper.recommend.toModel
import com.last.psychat.android.core.domain.entity.recommend.EmotionRequestEntity
import com.last.psychat.android.core.domain.entity.recommend.RecommendedContentListEntity
import com.last.psychat.android.core.domain.repository.RecommendRepository
import javax.inject.Inject

internal class RecommendRepositoryImpl @Inject constructor(
  private val dataSource: RecommendDataSource,
) : RecommendRepository {

  override suspend fun getRecommendedContentList(emotionRequestEntity: EmotionRequestEntity): RecommendedContentListEntity? {
    return dataSource.getRecommendedContentList(emotionRequestEntity.toModel())?.toEntity()
  }
}
