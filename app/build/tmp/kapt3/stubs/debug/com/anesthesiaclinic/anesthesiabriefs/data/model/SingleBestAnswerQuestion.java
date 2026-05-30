package com.anesthesiaclinic.anesthesiabriefs.data.model;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b>\b\u0087\b\u0018\u0000 S2\u00020\u0001:\u0002RSB\u00eb\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\r\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0016\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\tH\u00c6\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u00c6\u0003J\t\u0010>\u001a\u00020\rH\u00c6\u0003J\u000f\u0010?\u001a\b\u0012\u0004\u0012\u00020\t0\u000bH\u00c6\u0003J\t\u0010@\u001a\u00020\tH\u00c6\u0003J\t\u0010A\u001a\u00020\tH\u00c6\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bH\u00c6\u0003J\t\u0010F\u001a\u00020\u0016H\u00c6\u0003J\t\u0010G\u001a\u00020\rH\u00c6\u0003J\t\u0010H\u001a\u00020\u0016H\u00c6\u0003J\t\u0010I\u001a\u00020\u0016H\u00c6\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u0016H\u00c6\u0003J\u0085\u0002\u0010M\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\r2\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0016H\u00c6\u0001J\u0013\u0010N\u001a\u00020\u00162\b\u0010O\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010P\u001a\u00020\rH\u00d6\u0001J\t\u0010Q\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010(R\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010(R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010 R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010(R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0017\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010*R\u0011\u0010\u0018\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u00103R\u0011\u0010\u0019\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u00103R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010&R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010 R\u0011\u0010\u001c\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u00103\u00a8\u0006T"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/SingleBestAnswerQuestion;", "", "id", "", "boardType", "category", "topic", "difficulty", "stem", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;", "options", "", "correctAnswerIndex", "", "optionExplanations", "overallExplanation", "keyLearningPoint", "commonTrap", "tags", "sourceType", "referenceNotes", "officialEndorsement", "", "version", "isActive", "isDeprecated", "deprecatedReason", "replacedByQuestionId", "isBookmarked", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Ljava/util/List;ILjava/util/List;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Ljava/util/List;Ljava/lang/String;Ljava/util/List;ZIZZLcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getBoardType", "getCategory", "getTopic", "getDifficulty", "getStem", "()Lcom/anesthesiaclinic/anesthesiabriefs/data/model/LocalizedText;", "getOptions", "()Ljava/util/List;", "getCorrectAnswerIndex", "()I", "getOptionExplanations", "getOverallExplanation", "getKeyLearningPoint", "getCommonTrap", "getTags", "getSourceType", "getReferenceNotes", "getOfficialEndorsement", "()Z", "getVersion", "getDeprecatedReason", "getReplacedByQuestionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "equals", "other", "hashCode", "toString", "$serializer", "Companion", "app_debug"})
public final class SingleBestAnswerQuestion {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String boardType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String category = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String topic = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String difficulty = null;
    @org.jetbrains.annotations.NotNull()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText stem = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> options = null;
    private final int correctAnswerIndex = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> optionExplanations = null;
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
    public static final com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion.Companion Companion = null;
    
    public SingleBestAnswerQuestion(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String topic, @org.jetbrains.annotations.NotNull()
    java.lang.String difficulty, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText stem, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> options, int correctAnswerIndex, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> optionExplanations, @org.jetbrains.annotations.NotNull()
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
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> getOptions() {
        return null;
    }
    
    public final int getCorrectAnswerIndex() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> getOptionExplanations() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component15() {
        return null;
    }
    
    public final boolean component16() {
        return false;
    }
    
    public final int component17() {
        return 0;
    }
    
    public final boolean component18() {
        return false;
    }
    
    public final boolean component19() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component21() {
        return null;
    }
    
    public final boolean component22() {
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
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String topic, @org.jetbrains.annotations.NotNull()
    java.lang.String difficulty, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText stem, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> options, int correctAnswerIndex, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.LocalizedText> optionExplanations, @org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"com/anesthesiaclinic/anesthesiabriefs/data/model/SingleBestAnswerQuestion.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/SingleBestAnswerQuestion;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> {
        @org.jetbrains.annotations.NotNull()
        public static final com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion.$serializer INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final kotlinx.serialization.descriptors.SerialDescriptor descriptor = null;
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        public final void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion value) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/SingleBestAnswerQuestion$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/SingleBestAnswerQuestion;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> serializer() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}