package com.nhhackaton.data.document.source;

import java.io.File;

import okhttp3.MultipartBody;

public class DocumentRepository implements DocumentSource{

    private static DocumentRepository documentRepository;
    private DocumentRemoteDataSource documentRemoteDataSource;

    public static DocumentRepository getInstance() {

        if(documentRepository == null) {
            documentRepository = new DocumentRepository();
        }

        return documentRepository;

    }

    private DocumentRepository() {

        documentRemoteDataSource = DocumentRemoteDataSource.getInstance();
    }

    
    @Override
    public void callGetUri(String identity, File file, DocumentApiListener listener) {
        documentRemoteDataSource.callGetUri(identity, file, listener);
    }
}
