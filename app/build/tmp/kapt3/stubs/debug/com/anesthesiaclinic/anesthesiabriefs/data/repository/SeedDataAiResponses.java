package com.anesthesiaclinic.anesthesiabriefs.data.repository;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J(\u0010\r\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0011\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0012\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0013\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0014\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0015\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0016\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0017\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0018\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u0019\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u001a\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u001b\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u001c\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u001d\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J(\u0010\u001e\u001a\u00020\u000e2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0002J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\b2\u0006\u0010 \u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\"R3\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006#"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/SeedDataAiResponses;", "", "<init>", "()V", "responsesMap", "", "", "Lkotlin/Pair;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/AiStructuredResponse;", "getResponsesMap", "()Ljava/util/Map;", "responsesMap$delegate", "Lkotlin/Lazy;", "initPart1", "", "map", "", "initPart2", "initPart3", "initPart4", "initPart5", "initPart6", "initPart7", "initPart8", "initPart9", "initPart10", "initPart11", "initPart12", "initPart13", "initPart14", "initPart15", "getStructuredResponse", "prompt", "isEnglish", "", "app_debug"})
public final class SeedDataAiResponses {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy responsesMap$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.repository.SeedDataAiResponses INSTANCE = null;
    
    private SeedDataAiResponses() {
        super();
    }
    
    private final java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> getResponsesMap() {
        return null;
    }
    
    private final void initPart1(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart2(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart3(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart4(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart5(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart6(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart7(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart8(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart9(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart10(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart11(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart12(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart13(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart14(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    private final void initPart15(java.util.Map<java.lang.String, kotlin.Pair<com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse, com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse>> map) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.AiStructuredResponse getStructuredResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String prompt, boolean isEnglish) {
        return null;
    }
}