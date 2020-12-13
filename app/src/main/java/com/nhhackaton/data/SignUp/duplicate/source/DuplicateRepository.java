package com.nhhackaton.data.SignUp.duplicate.source;

public class DuplicateRepository implements DuplicateSource {

    private static DuplicateRepository duplicateRepository;
    private DuplicateRemoteDataSource duplicateRemoteDataSource;

    public static DuplicateRepository getInstance() {
        if(duplicateRepository == null) {
            duplicateRepository = new DuplicateRepository();
        }

        return duplicateRepository;
    }

    private DuplicateRepository() {
        duplicateRemoteDataSource = DuplicateRemoteDataSource.getInstance();
    }
    
    @Override
    public void callDuplicate(String identity, DuplicateApiListener listener) {
        duplicateRemoteDataSource.callDuplicate(identity, listener);
    }
}
