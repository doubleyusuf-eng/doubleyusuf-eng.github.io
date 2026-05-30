package com.anesthesiaclinic.anesthesiabriefs.data.repository;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001e\u001a\u00020\u001aJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\r2\u0006\u0010 \u001a\u00020\u001aJ\u0010\u0010!\u001a\u0004\u0018\u00010\u00112\u0006\u0010 \u001a\u00020\"J\u0010\u0010#\u001a\u0004\u0018\u00010\u00152\u0006\u0010$\u001a\u00020\u001aJ\u000e\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\u001aJ\u000e\u0010(\u001a\u00020)2\u0006\u0010\'\u001a\u00020\u001aJ\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001aR\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00060\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00060\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00060\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000b\u00a8\u0006-"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/FirestoreRepository;", "", "<init>", "()V", "_drugsList", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug;", "drugsList", "Lkotlinx/coroutines/flow/StateFlow;", "getDrugsList", "()Lkotlinx/coroutines/flow/StateFlow;", "_algorithmsList", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Algorithm;", "algorithmsList", "getAlgorithmsList", "_articlesList", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;", "articlesList", "getArticlesList", "_calculatorsList", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Calculator;", "calculatorsList", "getCalculatorsList", "_favoritesList", "", "", "favoritesList", "getFavoritesList", "getDrugById", "drugId", "getAlgorithmById", "id", "getArticleById", "", "getCalculatorBySlug", "slug", "isFavorite", "", "itemId", "toggleFavorite", "", "searchAll", "Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/SearchResults;", "query", "app_debug"})
public final class FirestoreRepository {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug>> _drugsList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug>> drugsList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm>> _algorithmsList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm>> algorithmsList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article>> _articlesList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article>> articlesList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator>> _calculatorsList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator>> calculatorsList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _favoritesList = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> favoritesList = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.repository.FirestoreRepository INSTANCE = null;
    
    private FirestoreRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug>> getDrugsList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm>> getAlgorithmsList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article>> getArticlesList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator>> getCalculatorsList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> getFavoritesList() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.Drug getDrugById(@org.jetbrains.annotations.NotNull()
    java.lang.String drugId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm getAlgorithmById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.Article getArticleById(int id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator getCalculatorBySlug(@org.jetbrains.annotations.NotNull()
    java.lang.String slug) {
        return null;
    }
    
    public final boolean isFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String itemId) {
        return false;
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String itemId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.repository.SearchResults searchAll(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
}