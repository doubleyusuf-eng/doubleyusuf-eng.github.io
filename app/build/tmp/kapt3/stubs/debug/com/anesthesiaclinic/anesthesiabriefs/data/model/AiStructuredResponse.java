package com.anesthesiaclinic.anesthesiabriefs.data.model;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0002ABB\u00e7\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\u0004\b\u0016\u0010\u0017J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH\u00c6\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH\u00c6\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u00ed\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0001J\u0013\u0010<\u001a\u00020\u00052\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020?H\u00d6\u0001J\t\u0010@\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010 R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010 \u00a8\u0006C"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/AiStructuredResponse;", "", "safetyLevel", "", "patientIdentifierDetected", "", "userAppearsToBeClinician", "notASubstituteWarning", "conversationalText", "immediateRedFlags", "", "missingCriticalInformation", "likelyClinicalCategoriesToConsider", "suggestedNextStepsGeneral", "relatedAlgorithms", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/AiSuggestionItem;", "relatedCalculators", "relatedDrugCards", "doNotMiss", "whenToEscalate", "referencesToShow", "followUpQuestions", "<init>", "(Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getSafetyLevel", "()Ljava/lang/String;", "getPatientIdentifierDetected", "()Z", "getUserAppearsToBeClinician", "getNotASubstituteWarning", "getConversationalText", "getImmediateRedFlags", "()Ljava/util/List;", "getMissingCriticalInformation", "getLikelyClinicalCategoriesToConsider", "getSuggestedNextStepsGeneral", "getRelatedAlgorithms", "getRelatedCalculators", "getRelatedDrugCards", "getDoNotMiss", "getWhenToEscalate", "getReferencesToShow", "getFollowUpQuestions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "equals", "other", "hashCode", "", "toString", "$serializer", "Companion", "app_debug"})
public final class AiStructuredResponse {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String safetyLevel = null;
    private final boolean patientIdentifierDetected = false;
    private final boolean userAppearsToBeClinician = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notASubstituteWarning = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String conversationalText = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> immediateRedFlags = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> missingCriticalInformation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> likelyClinicalCategoriesToConsider = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> suggestedNextStepsGeneral = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedAlgorithms = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedCalculators = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedDrugCards = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> doNotMiss = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> whenToEscalate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> referencesToShow = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> followUpQuestions = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse.Companion Companion = null;
    
    public AiStructuredResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String safetyLevel, boolean patientIdentifierDetected, boolean userAppearsToBeClinician, @org.jetbrains.annotations.NotNull()
    java.lang.String notASubstituteWarning, @org.jetbrains.annotations.Nullable()
    java.lang.String conversationalText, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> immediateRedFlags, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> missingCriticalInformation, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> likelyClinicalCategoriesToConsider, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> suggestedNextStepsGeneral, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedAlgorithms, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedCalculators, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedDrugCards, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> doNotMiss, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> whenToEscalate, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> referencesToShow, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> followUpQuestions) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSafetyLevel() {
        return null;
    }
    
    public final boolean getPatientIdentifierDetected() {
        return false;
    }
    
    public final boolean getUserAppearsToBeClinician() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotASubstituteWarning() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getConversationalText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getImmediateRedFlags() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getMissingCriticalInformation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLikelyClinicalCategoriesToConsider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSuggestedNextStepsGeneral() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> getRelatedAlgorithms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> getRelatedCalculators() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> getRelatedDrugCards() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getDoNotMiss() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getWhenToEscalate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getReferencesToShow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getFollowUpQuestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component16() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String safetyLevel, boolean patientIdentifierDetected, boolean userAppearsToBeClinician, @org.jetbrains.annotations.NotNull()
    java.lang.String notASubstituteWarning, @org.jetbrains.annotations.Nullable()
    java.lang.String conversationalText, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> immediateRedFlags, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> missingCriticalInformation, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> likelyClinicalCategoriesToConsider, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> suggestedNextStepsGeneral, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedAlgorithms, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedCalculators, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.AiSuggestionItem> relatedDrugCards, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> doNotMiss, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> whenToEscalate, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> referencesToShow, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> followUpQuestions) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"com/anesthesiaclinic/anesthesiabriefs/data/model/AiStructuredResponse.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/AiStructuredResponse;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse> {
        @org.jetbrains.annotations.NotNull()
        public static final com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse.$serializer INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final kotlinx.serialization.descriptors.SerialDescriptor descriptor = null;
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        public final void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse value) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/AiStructuredResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/AiStructuredResponse;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse> serializer() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}