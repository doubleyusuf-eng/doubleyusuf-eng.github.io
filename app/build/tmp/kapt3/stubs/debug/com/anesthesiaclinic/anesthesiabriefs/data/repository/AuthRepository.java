package com.anesthesiaclinic.anesthesiabriefs.data.repository;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000b\u001a\u00020\fJ0\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0010J,\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000eJ\u000e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0010J\u0006\u0010\u001e\u001a\u00020\fR\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001f"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/repository/AuthRepository;", "", "<init>", "()V", "_currentUserProfile", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/UserProfile;", "currentUserProfile", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentUserProfile", "()Lkotlinx/coroutines/flow/StateFlow;", "loginAsGuest", "", "registerUser", "", "fullName", "", "email", "specialty", "institution", "diplomaNumber", "acceptDisclaimer", "setDisclaimerAccepted", "dateStr", "loginUser", "name", "togglePremiumStatus", "status", "updateLanguage", "lang", "logout", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.anesthesiaclinic.anesthesiabriefs.data.model.UserProfile> _currentUserProfile = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.anesthesiaclinic.anesthesiabriefs.data.model.UserProfile> currentUserProfile = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.repository.AuthRepository INSTANCE = null;
    
    private AuthRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.anesthesiaclinic.anesthesiabriefs.data.model.UserProfile> getCurrentUserProfile() {
        return null;
    }
    
    public final void loginAsGuest() {
    }
    
    public final boolean registerUser(@org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String specialty, @org.jetbrains.annotations.NotNull()
    java.lang.String institution, @org.jetbrains.annotations.Nullable()
    java.lang.String diplomaNumber) {
        return false;
    }
    
    public final void acceptDisclaimer() {
    }
    
    public final void setDisclaimerAccepted(@org.jetbrains.annotations.NotNull()
    java.lang.String dateStr) {
    }
    
    public final boolean loginUser(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String specialty, @org.jetbrains.annotations.NotNull()
    java.lang.String institution) {
        return false;
    }
    
    public final void togglePremiumStatus(boolean status) {
    }
    
    public final void updateLanguage(@org.jetbrains.annotations.NotNull()
    java.lang.String lang) {
    }
    
    public final void logout() {
    }
}