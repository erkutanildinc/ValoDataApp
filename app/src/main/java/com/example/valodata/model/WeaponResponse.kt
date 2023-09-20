package com.example.valodata.model

data class WeaponsResponse (
    val status: Long,
    val data: List<Weapon>
)

data class Weapon (
    val uuid: String,
    val displayName: String,
    val category: String,
    val defaultSkinUUID: String,
    val displayIcon: String,
    val killStreamIcon: String,
    val assetPath: String,
    val weaponStats: WeaponStats? = null,
    val shopData: ShopData? = null,
    val skins: List<Skin>
)

data class ShopData (
    val cost: Long,
    val category: String,
    val categoryText: String,
    val gridPosition: GridPosition? = null,
    val canBeTrashed: Boolean,
    val image: Any? = null,
    val newImage: String,
    val newImage2: Any? = null,
    val assetPath: String
)

data class GridPosition (
    val row: Long,
    val column: Long
)

data class Skin (
    val uuid: String,
    val displayName: String,
    val themeUUID: String,
    val contentTierUUID: String? = null,
    val displayIcon: String? = null,
    val wallpaper: String? = null,
    val assetPath: String,
    val chromas: List<Chroma>,
    val levels: List<Level>
)

data class Chroma (
    val uuid: String,
    val displayName: String,
    val displayIcon: String? = null,
    val fullRender: String,
    val swatch: String? = null,
    val streamedVideo: String? = null,
    val assetPath: String
)

data class Level (
    val uuid: String,
    val displayName: String,
    val levelItem: LevelItem? = null,
    val displayIcon: String? = null,
    val streamedVideo: String? = null,
    val assetPath: String
)

enum class LevelItem {
    EEquippableSkinLevelItemAnimation,
    EEquippableSkinLevelItemAttackerDefenderSwap,
    EEquippableSkinLevelItemFinisher,
    EEquippableSkinLevelItemFishAnimation,
    EEquippableSkinLevelItemHeartbeatAndMapSensor,
    EEquippableSkinLevelItemInspectAndKill,
    EEquippableSkinLevelItemKillBanner,
    EEquippableSkinLevelItemKillCounter,
    EEquippableSkinLevelItemKillEffect,
    EEquippableSkinLevelItemRandomizer,
    EEquippableSkinLevelItemSoundEffects,
    EEquippableSkinLevelItemTopFrag,
    EEquippableSkinLevelItemTransformation,
    EEquippableSkinLevelItemVFX,
    EEquippableSkinLevelItemVoiceover
}

data class WeaponStats (
    val fireRate: Double,
    val magazineSize: Long,
    val runSpeedMultiplier: Double,
    val equipTimeSeconds: Double,
    val reloadTimeSeconds: Double,
    val firstBulletAccuracy: Double,
    val shotgunPelletCount: Long,
    val wallPenetration: WallPenetration,
    val feature: String? = null,
    val fireMode: String? = null,
    val altFireType: AltFireType? = null,
    val adsStats: AdsStats? = null,
    val altShotgunStats: AltShotgunStats? = null,
    val airBurstStats: AirBurstStats? = null,
    val damageRanges: List<DamageRange>
)

data class AdsStats (
    val zoomMultiplier: Double,
    val fireRate: Double,
    val runSpeedMultiplier: Double,
    val burstCount: Long,
    val firstBulletAccuracy: Double
)

data class AirBurstStats (
    val shotgunPelletCount: Long,
    val burstDistance: Double
)

enum class AltFireType {
    EWeaponAltFireDisplayTypeADS,
    EWeaponAltFireDisplayTypeAirBurst,
    EWeaponAltFireDisplayTypeShotgun
}

data class AltShotgunStats (
    val shotgunPelletCount: Long,
    val burstRate: Double
)

data class DamageRange (
    val rangeStartMeters: Long,
    val rangeEndMeters: Long,
    val headDamage: Double,
    val bodyDamage: Long,
    val legDamage: Double
)

enum class WallPenetration {
    EWallPenetrationDisplayTypeHigh,
    EWallPenetrationDisplayTypeLow,
    EWallPenetrationDisplayTypeMedium
}
