package com.anesthesiaclinic.anesthesiabriefs.data.sync;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ \u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0002\u0010\u0011J(\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/sync/FirebaseSyncService;", "", "<init>", "()V", "TAG", "", "jsonConfig", "Lkotlinx/serialization/json/Json;", "calculateSha256", "content", "isFirebaseAvailable", "", "context", "Landroid/content/Context;", "checkForUpdates", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPackManifest;", "packId", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPack", "manifest", "forceLocal", "(Landroid/content/Context;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPackManifest;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSimulatedManifest", "getSimulatedUpdatedPackJson", "app_debug"})
public final class FirebaseSyncService {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "FirebaseSyncService";
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.serialization.json.Json jsonConfig = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.sync.FirebaseSyncService INSTANCE = null;
    
    private FirebaseSyncService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String calculateSha256(@org.jetbrains.annotations.NotNull()
    java.lang.String content) {
        return null;
    }
    
    public final boolean isFirebaseAvailable(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Checks if there's a newer version of the pack.
     * Returns the remote manifest if a newer version is available, otherwise null.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkForUpdates(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String packId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPackManifest> $completion) {
        return null;
    }
    
    /**
     * Downloads and imports the specified pack.
     * Executes SHA-256 validation. Throws exception if validation fails.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncPack(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPackManifest manifest, boolean forceLocal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPackManifest getSimulatedManifest(java.lang.String packId, android.content.Context context) {
        return null;
    }
    
    private final java.lang.String getSimulatedUpdatedPackJson(java.lang.String packId, android.content.Context context) {
        return null;
    }
}