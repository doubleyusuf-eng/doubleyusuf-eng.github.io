package com.anesthesiaclinic.anesthesiabriefs.data.repository;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\nJ\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/BoardPrepAccessManager;", "", "<init>", "()V", "PREFS_NAME", "", "KEY_PREMIUM_UNLOCKED", "DEMO_QUESTION_IDS", "", "isPremiumUser", "", "context", "Landroid/content/Context;", "setPremiumUnlocked", "", "unlocked", "isBoardPrepDemoAllowed", "questionId", "canAccessFullBoardPrep", "canAccessExamMode", "canAccessWeakTopics", "canAccessAnalytics", "canAccessFullViva", "canAccessCloudSync", "app_debug"})
public final class BoardPrepAccessManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "anesthesia_briefs_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_PREMIUM_UNLOCKED = "board_prep_premium_unlocked";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> DEMO_QUESTION_IDS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepAccessManager INSTANCE = null;
    
    private BoardPrepAccessManager() {
        super();
    }
    
    public final boolean isPremiumUser(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final void setPremiumUnlocked(@org.jetbrains.annotations.NotNull()
    android.content.Context context, boolean unlocked) {
    }
    
    public final boolean isBoardPrepDemoAllowed(@org.jetbrains.annotations.NotNull()
    java.lang.String questionId) {
        return false;
    }
    
    public final boolean canAccessFullBoardPrep(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean canAccessExamMode(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean canAccessWeakTopics(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean canAccessAnalytics(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean canAccessFullViva(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean canAccessCloudSync(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}