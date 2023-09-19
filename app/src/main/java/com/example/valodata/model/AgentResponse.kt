package com.example.valodata.model

data class AgentResponse (
    val status: Long,
    val data: List<Agent>
)

data class Agent (
    val uuid: String,
    val displayName: String,
    val description: String,
    val developerName: String,
    val characterTags: List<String>? = null,
    val displayIcon: String,
    val displayIconSmall: String,
    val bustPortrait: String? = null,
    val fullPortrait: String? = null,
    val fullPortraitV2: String? = null,
    val killfeedPortrait: String,
    val background: String? = null,
    val backgroundGradientColors: List<String>,
    val assetPath: String,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val role: Role? = null,
    val recruitmentData: RecruitmentData? = null,
    val abilities: List<Ability>,
    val voiceLine: VoiceLine
)

data class Ability (
    val slot: Slot,
    val displayName: String,
    val description: String,
    val displayIcon: String? = null
)

enum class Slot {
    Ability1,
    Ability2,
    Grenade,
    Passive,
    Ultimate
}

data class RecruitmentData (
    val counterID: String,
    val milestoneID: String,
    val milestoneThreshold: Long,
    val useLevelVpCostOverride: Boolean,
    val levelVpCostOverride: Long,
    val startDate: String,
    val endDate: String
)

data class Role (
    val uuid: String,
    val displayName: DisplayName,
    val description: String,
    val displayIcon: String,
    val assetPath: String
)

enum class DisplayName {
    Controller,
    Duelist,
    Initiator,
    Sentinel
}

data class VoiceLine (
    val minDuration: Double,
    val maxDuration: Double,
    val mediaList: List<MediaList>
)

data class MediaList (
    val id: Long,
    val wwise: String,
    val wave: String
)