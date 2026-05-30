package com.anesthesiaclinic.anesthesiabriefs.data.repository;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003JI\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/SearchResults;", "", "calculators", "", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Calculator;", "drugs", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug;", "algorithms", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Algorithm;", "articles", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getCalculators", "()Ljava/util/List;", "getDrugs", "getAlgorithms", "getArticles", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class SearchResults {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator> calculators = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> drugs = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm> algorithms = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> articles = null;
    
    public SearchResults(@org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator> calculators, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> drugs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm> algorithms, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> articles) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator> getCalculators() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> getDrugs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm> getAlgorithms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> getArticles() {
        return null;
    }
    
    public SearchResults() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.repository.SearchResults copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Calculator> calculators, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> drugs, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Algorithm> algorithms, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> articles) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}