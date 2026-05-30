package com.anesthesiaclinic.anesthesiabriefs.data.model;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b;\b\u0087\b\u0018\u0000 Q2\u00020\u0001:\u0002PQB\u00dd\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0015\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\nH\u00c6\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\t\u0010>\u001a\u00020\nH\u00c6\u0003J\t\u0010?\u001a\u00020\nH\u00c6\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u00c6\u0003J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010C\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fH\u00c6\u0003J\t\u0010D\u001a\u00020\u0015H\u00c6\u0003J\t\u0010E\u001a\u00020\u0017H\u00c6\u0003J\t\u0010F\u001a\u00020\u0015H\u00c6\u0003J\t\u0010G\u001a\u00020\u0015H\u00c6\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0015H\u00c6\u0003J\u00f5\u0001\u0010K\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\b\b\u0002\u0010\u0012\u001a\u00020\u00032\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u00152\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0015H\u00c6\u0001J\u0013\u0010L\u001a\u00020\u00152\b\u0010M\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010N\u001a\u00020\u0017H\u00d6\u0001J\t\u0010O\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u000e\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\'R\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\'R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\'R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010)R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0018\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u00101R\u0011\u0010\u0019\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u00101R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\'R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010 R\u0011\u0010\u001c\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u00101\u00a8\u0006R"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfQuestion;", "", "id", "", "boardType", "paper", "category", "topic", "difficulty", "stem", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;", "statements", "", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfStatement;", "overallExplanation", "keyLearningPoint", "commonTrap", "tags", "sourceType", "referenceNotes", "officialEndorsement", "", "version", "", "isActive", "isDeprecated", "deprecatedReason", "replacedByQuestionId", "isBookmarked", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Ljava/util/List;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Ljava/util/List;Ljava/lang/String;Ljava/util/List;ZIZZLcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getBoardType", "getPaper", "getCategory", "getTopic", "getDifficulty", "getStem", "()Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;", "getStatements", "()Ljava/util/List;", "getOverallExplanation", "getKeyLearningPoint", "getCommonTrap", "getTags", "getSourceType", "getReferenceNotes", "getOfficialEndorsement", "()Z", "getVersion", "()I", "getDeprecatedReason", "getReplacedByQuestionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "copy", "equals", "other", "hashCode", "toString", "$serializer", "Companion", "app_debug"})
public final class EdaicMtfQuestion {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String boardType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String paper = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String category = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String topic = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String difficulty = null;
    @org.jetbrains.annotations.NotNull()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText stem = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfStatement> statements = null;
    @org.jetbrains.annotations.NotNull()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText overallExplanation = null;
    @org.jetbrains.annotations.NotNull()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText keyLearningPoint = null;
    @org.jetbrains.annotations.Nullable()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText commonTrap = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> tags = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String sourceType = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.String> referenceNotes = null;
    private final boolean officialEndorsement = false;
    private final int version = 0;
    private final boolean isActive = false;
    private final boolean isDeprecated = false;
    @org.jetbrains.annotations.Nullable()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText deprecatedReason = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String replacedByQuestionId = null;
    private final boolean isBookmarked = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion.Companion Companion = null;
    
    public EdaicMtfQuestion(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    java.lang.String paper, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String topic, @org.jetbrains.annotations.NotNull()
    java.lang.String difficulty, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText stem, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfStatement> statements, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText overallExplanation, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText keyLearningPoint, @org.jetbrains.annotations.Nullable()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText commonTrap, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    java.lang.String sourceType, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> referenceNotes, boolean officialEndorsement, int version, boolean isActive, boolean isDeprecated, @org.jetbrains.annotations.Nullable()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText deprecatedReason, @org.jetbrains.annotations.Nullable()
    java.lang.String replacedByQuestionId, boolean isBookmarked) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBoardType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPaper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTopic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDifficulty() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText getStem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfStatement> getStatements() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText getOverallExplanation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText getKeyLearningPoint() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText getCommonTrap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSourceType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getReferenceNotes() {
        return null;
    }
    
    public final boolean getOfficialEndorsement() {
        return false;
    }
    
    public final int getVersion() {
        return 0;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    public final boolean isDeprecated() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText getDeprecatedReason() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReplacedByQuestionId() {
        return null;
    }
    
    public final boolean isBookmarked() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component14() {
        return null;
    }
    
    public final boolean component15() {
        return false;
    }
    
    public final int component16() {
        return 0;
    }
    
    public final boolean component17() {
        return false;
    }
    
    public final boolean component18() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
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
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfStatement> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    java.lang.String paper, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String topic, @org.jetbrains.annotations.NotNull()
    java.lang.String difficulty, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText stem, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfStatement> statements, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText overallExplanation, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText keyLearningPoint, @org.jetbrains.annotations.Nullable()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText commonTrap, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    java.lang.String sourceType, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> referenceNotes, boolean officialEndorsement, int version, boolean isActive, boolean isDeprecated, @org.jetbrains.annotations.Nullable()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText deprecatedReason, @org.jetbrains.annotations.Nullable()
    java.lang.String replacedByQuestionId, boolean isBookmarked) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"com/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfQuestion.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfQuestion;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> {
        @org.jetbrains.annotations.NotNull()
        public static final com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion.$serializer INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final kotlinx.serialization.descriptors.SerialDescriptor descriptor = null;
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        public final void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion value) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfQuestion$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfQuestion;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> serializer() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}