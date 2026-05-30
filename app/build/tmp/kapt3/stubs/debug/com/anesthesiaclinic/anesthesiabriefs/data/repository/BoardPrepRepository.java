package com.anesthesiaclinic.anesthesiabriefs.data.repository;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013J$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J \u0010\u0019\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ&\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010 J \u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018JZ\u0010#\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\'2\b\b\u0002\u0010(\u001a\u00020\t2\b\b\u0002\u0010)\u001a\u00020\t2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170\u0015H\u0086@\u00a2\u0006\u0002\u0010+Jf\u0010,\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\'2\b\b\u0002\u0010(\u001a\u00020\t2\b\b\u0002\u0010)\u001a\u00020\t2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170\u00152\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0017H\u0086@\u00a2\u0006\u0002\u0010.Jj\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u00103\u001a\u00020\t2\b\b\u0002\u00104\u001a\u00020\t2\b\b\u0002\u00105\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u00106J\u001c\u00107\u001a\b\u0012\u0004\u0012\u00020\u00170\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u00108\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u00109\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\"\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00170\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ(\u0010=\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010;0\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ$\u0010>\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\"0\u0015H\u0086@\u00a2\u0006\u0002\u0010@J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u001c\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/BoardPrepRepository;", "", "<init>", "()V", "database", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDatabase;", "dao", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDao;", "isInitialized", "", "jsonConfig", "Lkotlinx/serialization/json/Json;", "initialize", "", "context", "Landroid/content/Context;", "getDao", "inflateQuestion", "entity", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/QuestionEntity;", "getQuestionsByBoardType", "", "boardType", "", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQuestionById", "id", "getBookmarkedQuestions", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleBookmark", "questionId", "isBookmarked", "(Landroid/content/Context;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserProgress", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/UserProgressEntity;", "saveUserProgress", "selectedAnswersJson", "isCorrectStrict", "statementsCorrectCount", "", "isBookmarkToggleOnly", "markedDifficult", "tags", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZIZZLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveUserProgressWithRating", "vivaSelfRating", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZIZZLjava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQuestionsFiltered", "packId", "category", "difficulty", "bookmarkedOnly", "unansweredOnly", "incorrectOnly", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWeakTopics", "resetLocalCache", "clearAllProgress", "getStats", "", "getAllCategories", "getInstalledPacksRich", "mergeLocalAndCloudProgress", "cloudProgressList", "(Landroid/content/Context;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBoardSpotNotes", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardSpotNote;", "getDailyClinicalPearls", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/DailyClinicalPearl;", "app_debug"})
public final class BoardPrepRepository {
    private static com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDatabase database;
    private static com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDao dao;
    private static boolean isInitialized = false;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.serialization.json.Json jsonConfig = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.repository.BoardPrepRepository INSTANCE = null;
    
    private BoardPrepRepository() {
        super();
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDao getDao(android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Object inflateQuestion(@org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity entity) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuestionsByBoardType(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.lang.Object>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuestionById(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<java.lang.Object> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBookmarkedQuestions(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.lang.Object>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleBookmark(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String questionId, boolean isBookmarked, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUserProgress(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String questionId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserProgress(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String questionId, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedAnswersJson, boolean isCorrectStrict, int statementsCorrectCount, boolean isBookmarkToggleOnly, boolean markedDifficult, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveUserProgressWithRating(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String questionId, @org.jetbrains.annotations.NotNull()
    java.lang.String selectedAnswersJson, boolean isCorrectStrict, int statementsCorrectCount, boolean isBookmarkToggleOnly, boolean markedDifficult, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.Nullable()
    java.lang.String vivaSelfRating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getQuestionsFiltered(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String boardType, @org.jetbrains.annotations.Nullable()
    java.lang.String packId, @org.jetbrains.annotations.Nullable()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.String difficulty, boolean bookmarkedOnly, boolean unansweredOnly, boolean incorrectOnly, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.lang.Object>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWeakTopics(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resetLocalCache(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAllProgress(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getStats(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.lang.Object>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getInstalledPacksRich(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object mergeLocalAndCloudProgress(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity> cloudProgressList, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBoardSpotNotes(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.BoardSpotNote>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDailyClinicalPearls(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.DailyClinicalPearl>> $completion) {
        return null;
    }
}