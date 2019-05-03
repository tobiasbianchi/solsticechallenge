package com.tobias.solsticechallenge.services;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.serializer.JsonContactSchema;
import com.tobias.solsticechallenge.serializer.JsonToContact;
import com.tobias.solsticechallenge.utils.HttpRequest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadContactsTask extends AsyncTask<Void,Void,HttpRequest.Response> {
    private LoadedContactsInterface loadedContactsInterface;

    public LoadContactsTask(LoadedContactsInterface loadedContactsInterface){
        this.loadedContactsInterface = loadedContactsInterface;
    }

    @Override
    protected HttpRequest.Response  doInBackground(Void... voids) {
         return new HttpRequest("https://s3.amazonaws.com/technical-challenge/v3/contacts.json")
                .doRequest(connection -> connection.setRequestMethod("GET"));
    }

    @Override
    protected void onPostExecute(HttpRequest.Response response) {
        if(response.requestWasResolved() && HttpRequest.successResponse(response.getCode())){
            try {
                JsonNode schemaNode = JsonLoader.fromString(JsonContactSchema.CONTACT_SCHEMA);
                JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);
                JsonNode arrayOfContacts = JsonLoader.fromString(response.getResponseText());
                if( schema.validate(arrayOfContacts).isSuccess() ) {
                    List<Contact> contacts = new ArrayList<>();
                    for (final JsonNode objNode : arrayOfContacts) {
                        contacts.add(JsonToContact.transform(objNode));
                    }
                    loadedContactsInterface.onLoadedContacts(contacts);
                    return;
                }
            } catch (IOException | ProcessingException ignored) {}
        } else {
            loadedContactsInterface.onFailedLoading(LoadedContactsInterface.ERROR.CONNECTION);
            return;
        }
        loadedContactsInterface.onFailedLoading(LoadedContactsInterface.ERROR.JSON_FORMAT);
    }
}

