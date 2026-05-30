package com.anesthesiaclinic.anesthesiabriefs.ui.screens;

@kotlin.Metadata(mv = {2, 0, 0}, k = 2, xi = 48, d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a.\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a$\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@\u00a2\u0006\u0002\u0010\u000e\u001a(\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010\u0012\u001a$\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a4\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001aH\u0007\u001a/\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0007\u00a2\u0006\u0004\b#\u0010$\u00a8\u0006%"}, d2 = {"CalculatorsListScreen", "", "currentLanguage", "", "onSelectCalculator", "Lkotlin/Function1;", "AlgorithmsListScreen", "filter", "onSelectAlgorithm", "DrugsListScreen", "onSelectDrug", "fetchRecentPubMedArticles", "", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateMonographFromGemini", "article", "key", "(Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "LiteratureListScreen", "onSelectArticle", "", "PubMedArticleCard", "isTurkish", "", "onViewMonographClick", "Lkotlin/Function0;", "onViewPubMedClick", "MonographDetailCard", "title", "content", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "accentColor", "Landroidx/compose/ui/graphics/Color;", "MonographDetailCard-g2O1Hgs", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;J)V", "app_debug"})
public final class ListScreensKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CalculatorsListScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String currentLanguage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSelectCalculator) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AlgorithmsListScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.lang.String currentLanguage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSelectAlgorithm) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DrugsListScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String currentLanguage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSelectDrug) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.Object fetchRecentPubMedArticles(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.Article>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public static final java.lang.Object generateMonographFromGemini(@org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.Article article, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String currentLanguage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.anesthesiaclinic.anesthesiabriefs.data.model.Article> $completion) {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void LiteratureListScreen(@org.jetbrains.annotations.NotNull()
    java.lang.String currentLanguage, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSelectArticle) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void PubMedArticleCard(@org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.Article article, boolean isTurkish, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onViewMonographClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onViewPubMedClick) {
    }
}