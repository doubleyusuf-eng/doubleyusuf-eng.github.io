package com.anesthesiaclinic.anesthesiabriefs.utils;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u0018\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0002J\u0018\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0002J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/utils/LocalSearchEngine;", "", "<init>", "()V", "jsonConfig", "Lkotlinx/serialization/json/Json;", "qaDatabase", "", "Lcom/anesthesiaclinic/anesthesiabriefs/utils/LocalQaItem;", "isInitialized", "", "STOP_WORDS", "", "", "initialize", "", "context", "Landroid/content/Context;", "initializeForTest", "jsonString", "getItemsCount", "", "tokenize", "text", "levenshteinDistance", "s1", "s2", "isFuzzyMatch", "w1", "w2", "findBestMatch", "query", "isEnglish", "app_debug"})
public final class LocalSearchEngine {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.serialization.json.Json jsonConfig = null;
    @org.jetbrains.annotations.NotNull()
    private static java.util.List<com.anesthesiaclinic.anesthesiabriefs.utils.LocalQaItem> qaDatabase;
    private static boolean isInitialized = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<java.lang.String> STOP_WORDS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.utils.LocalSearchEngine INSTANCE = null;
    
    private LocalSearchEngine() {
        super();
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void initializeForTest(@org.jetbrains.annotations.NotNull()
    java.lang.String jsonString) {
    }
    
    public final int getItemsCount() {
        return 0;
    }
    
    private final java.util.Set<java.lang.String> tokenize(java.lang.String text) {
        return null;
    }
    
    private final int levenshteinDistance(java.lang.String s1, java.lang.String s2) {
        return 0;
    }
    
    private final boolean isFuzzyMatch(java.lang.String w1, java.lang.String w2) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.utils.LocalQaItem findBestMatch(@org.jetbrains.annotations.NotNull()
    java.lang.String query, boolean isEnglish) {
        return null;
    }
}