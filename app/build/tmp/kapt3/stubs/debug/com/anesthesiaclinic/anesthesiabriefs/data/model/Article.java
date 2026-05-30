package com.anesthesiaclinic.anesthesiabriefs.data.model;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\bP\b\u0087\b\u0018\u0000 d2\u00020\u0001:\u0002cdB\u00ad\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0015\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\"\u0010#J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010E\u001a\u00020\u0005H\u00c6\u0003J\t\u0010F\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00c6\u0003J\t\u0010H\u001a\u00020\u0005H\u00c6\u0003J\t\u0010I\u001a\u00020\u0005H\u00c6\u0003J\t\u0010J\u001a\u00020\u0005H\u00c6\u0003J\t\u0010K\u001a\u00020\u0005H\u00c6\u0003J\t\u0010L\u001a\u00020\u0005H\u00c6\u0003J\t\u0010M\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010S\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010V\u001a\u00020\u0015H\u00c6\u0003J\u000f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00c6\u0003J\t\u0010X\u001a\u00020\u0015H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0015H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u00c1\u0002\u0010^\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00152\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00152\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010\u001d\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010_\u001a\u00020\u00152\b\u0010`\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010a\u001a\u00020\u0003H\u00d6\u0001J\t\u0010b\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\'R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\'R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\'R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\'R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\'R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\'R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\'R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010\'R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\'R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\'R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\'R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010%R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\'R\u0011\u0010\u0017\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u00107R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\'R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010\'R\u0011\u0010\u001a\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u00107R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010,R\u0011\u0010\u001c\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u00107R\u0011\u0010\u001d\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u00107R\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010%R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010\'R\u0013\u0010 \u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010\'R\u0013\u0010!\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010\'\u00a8\u0006e"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;", "", "id", "", "pmid", "", "doi", "titleTr", "titleEn", "authors", "", "journal", "publicationDate", "keyMessageTr", "studyDesignTr", "mainFindingsTr", "clinicalTakeawayTr", "limitationsTr", "practiceImpactTr", "impactRating", "aiGeneratedDraft", "", "aiCommentaryTr", "physicianReviewed", "reviewer", "lastReviewed", "requiresClinicalValidation", "categories", "isFeatured", "isPublished", "readingTimeMinutes", "sourceUrl", "instagramPostUrl", "instagramImageUrl", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/List;ZZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getPmid", "()Ljava/lang/String;", "getDoi", "getTitleTr", "getTitleEn", "getAuthors", "()Ljava/util/List;", "getJournal", "getPublicationDate", "getKeyMessageTr", "getStudyDesignTr", "getMainFindingsTr", "getClinicalTakeawayTr", "getLimitationsTr", "getPracticeImpactTr", "getImpactRating", "getAiGeneratedDraft", "()Z", "getAiCommentaryTr", "getPhysicianReviewed", "getReviewer", "getLastReviewed", "getRequiresClinicalValidation", "getCategories", "getReadingTimeMinutes", "getSourceUrl", "getInstagramPostUrl", "getInstagramImageUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "copy", "equals", "other", "hashCode", "toString", "$serializer", "Companion", "app_debug"})
public final class Article {
    private final int id = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String pmid = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String doi = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String titleTr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String titleEn = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> authors = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String journal = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String publicationDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String keyMessageTr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String studyDesignTr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String mainFindingsTr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String clinicalTakeawayTr = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String limitationsTr = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String practiceImpactTr = null;
    private final int impactRating = 0;
    private final boolean aiGeneratedDraft = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String aiCommentaryTr = null;
    private final boolean physicianReviewed = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String reviewer = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastReviewed = null;
    private final boolean requiresClinicalValidation = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> categories = null;
    private final boolean isFeatured = false;
    private final boolean isPublished = false;
    private final int readingTimeMinutes = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String sourceUrl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String instagramPostUrl = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String instagramImageUrl = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.model.Article.Companion Companion = null;
    
    public Article(int id, @org.jetbrains.annotations.Nullable()
    java.lang.String pmid, @org.jetbrains.annotations.Nullable()
    java.lang.String doi, @org.jetbrains.annotations.NotNull()
    java.lang.String titleTr, @org.jetbrains.annotations.NotNull()
    java.lang.String titleEn, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> authors, @org.jetbrains.annotations.NotNull()
    java.lang.String journal, @org.jetbrains.annotations.NotNull()
    java.lang.String publicationDate, @org.jetbrains.annotations.NotNull()
    java.lang.String keyMessageTr, @org.jetbrains.annotations.NotNull()
    java.lang.String studyDesignTr, @org.jetbrains.annotations.NotNull()
    java.lang.String mainFindingsTr, @org.jetbrains.annotations.NotNull()
    java.lang.String clinicalTakeawayTr, @org.jetbrains.annotations.Nullable()
    java.lang.String limitationsTr, @org.jetbrains.annotations.Nullable()
    java.lang.String practiceImpactTr, int impactRating, boolean aiGeneratedDraft, @org.jetbrains.annotations.Nullable()
    java.lang.String aiCommentaryTr, boolean physicianReviewed, @org.jetbrains.annotations.Nullable()
    java.lang.String reviewer, @org.jetbrains.annotations.Nullable()
    java.lang.String lastReviewed, boolean requiresClinicalValidation, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> categories, boolean isFeatured, boolean isPublished, int readingTimeMinutes, @org.jetbrains.annotations.Nullable()
    java.lang.String sourceUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String instagramPostUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String instagramImageUrl) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPmid() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDoi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitleTr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitleEn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAuthors() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJournal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPublicationDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKeyMessageTr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStudyDesignTr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMainFindingsTr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getClinicalTakeawayTr() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLimitationsTr() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPracticeImpactTr() {
        return null;
    }
    
    public final int getImpactRating() {
        return 0;
    }
    
    public final boolean getAiGeneratedDraft() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAiCommentaryTr() {
        return null;
    }
    
    public final boolean getPhysicianReviewed() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReviewer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastReviewed() {
        return null;
    }
    
    public final boolean getRequiresClinicalValidation() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getCategories() {
        return null;
    }
    
    public final boolean isFeatured() {
        return false;
    }
    
    public final boolean isPublished() {
        return false;
    }
    
    public final int getReadingTimeMinutes() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSourceUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getInstagramPostUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getInstagramImageUrl() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final boolean component16() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component17() {
        return null;
    }
    
    public final boolean component18() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component20() {
        return null;
    }
    
    public final boolean component21() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component22() {
        return null;
    }
    
    public final boolean component23() {
        return false;
    }
    
    public final boolean component24() {
        return false;
    }
    
    public final int component25() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.Article copy(int id, @org.jetbrains.annotations.Nullable()
    java.lang.String pmid, @org.jetbrains.annotations.Nullable()
    java.lang.String doi, @org.jetbrains.annotations.NotNull()
    java.lang.String titleTr, @org.jetbrains.annotations.NotNull()
    java.lang.String titleEn, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> authors, @org.jetbrains.annotations.NotNull()
    java.lang.String journal, @org.jetbrains.annotations.NotNull()
    java.lang.String publicationDate, @org.jetbrains.annotations.NotNull()
    java.lang.String keyMessageTr, @org.jetbrains.annotations.NotNull()
    java.lang.String studyDesignTr, @org.jetbrains.annotations.NotNull()
    java.lang.String mainFindingsTr, @org.jetbrains.annotations.NotNull()
    java.lang.String clinicalTakeawayTr, @org.jetbrains.annotations.Nullable()
    java.lang.String limitationsTr, @org.jetbrains.annotations.Nullable()
    java.lang.String practiceImpactTr, int impactRating, boolean aiGeneratedDraft, @org.jetbrains.annotations.Nullable()
    java.lang.String aiCommentaryTr, boolean physicianReviewed, @org.jetbrains.annotations.Nullable()
    java.lang.String reviewer, @org.jetbrains.annotations.Nullable()
    java.lang.String lastReviewed, boolean requiresClinicalValidation, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> categories, boolean isFeatured, boolean isPublished, int readingTimeMinutes, @org.jetbrains.annotations.Nullable()
    java.lang.String sourceUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String instagramPostUrl, @org.jetbrains.annotations.Nullable()
    java.lang.String instagramImageUrl) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"com/anesthesiaclinic/anesthesiabriefs/data/model/Article.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> {
        @org.jetbrains.annotations.NotNull()
        public static final com.anesthesiaclinic.anesthesiabriefs.data.model.Article.$serializer INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final kotlinx.serialization.descriptors.SerialDescriptor descriptor = null;
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.model.Article deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        public final void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.model.Article value) {
        }
        
        private $serializer() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Article;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.Article> serializer() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}