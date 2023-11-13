package com.last.psychat.android.core.domain.util

import java.io.IOException

// Login
val CreateLoginTokenResponseIsNull = IOException("Create LoginToken API response is null.")

// Chat
val EndChatSessionResponseIsNull = IOException("End Chat Session API response is null.")
val GetPreviousChatDetailResponseIsNull = IOException("Get Previous Chat Detail API is null.")
val PreviousChatListResponseIsNull = IOException("Get Previous ChatList API response is null.")
val StartChatSessionResponseIsNull = IOException("Start Chat Session API response is null.")
val SendChatMessageResponseIsNull = IOException("Send Chat Message API response is null.")
val CheckEmotionIsJudgedResponseIsNull = IOException("Check Emotion is Judged API response is null.")

// Recommend
val GetRecommendedContentListResponseIsNull = IOException("Get Recommended Content List API response is null.")
