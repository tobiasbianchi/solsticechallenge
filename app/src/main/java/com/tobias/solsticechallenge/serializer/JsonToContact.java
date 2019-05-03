package com.tobias.solsticechallenge.serializer;

import android.net.Uri;
import android.support.v4.util.Pair;

import com.fasterxml.jackson.databind.JsonNode;
import com.tobias.solsticechallenge.model.Contact;
import com.tobias.solsticechallenge.model.ContactAddress;
import com.tobias.solsticechallenge.model.ContactImageURL;
import com.tobias.solsticechallenge.model.ContactPhone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**Transforms a JsonNode that has the Json Structure of a Contact to its model representation */
public class JsonToContact {
    private static final List<Pair<String, ContactPhone.Type>> PHONE_PAIRS = Arrays
            .asList(new Pair<>("work", ContactPhone.Type.WORK),
                    new Pair<>("home", ContactPhone.Type.HOME),
                    new Pair<>("mobile", ContactPhone.Type.MOBILE));

    public static Contact transform(JsonNode node){
        String name = node.get("name").asText(),
                email = node.get("emailAddress").asText(),
                id = node.get("id").asText(),
                companyName = getComapnyName(node);
        Date birthdate = getBirthdate(node);
        boolean favorite = node.get("isFavorite").asBoolean();
        List<ContactPhone> phone = transformPhone(node.get("phone"));
        ContactAddress address = transformAddress(node.get("address"));
        ContactImageURL imageURL = transformURi(node);
        return new Contact(
                Integer.parseInt(id), name, companyName,
                email, birthdate, address, phone, imageURL, favorite);
    }

    private static Date getBirthdate(JsonNode node){
        String birthdateString = node.get("birthdate").asText();
        Date birthdate = new Date();
        try {
            birthdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(birthdateString);
        } catch (ParseException ignored) {} //ignoring because json was validated
        return birthdate;
    }

    private static String getComapnyName(JsonNode node){
        JsonNode companyNameNode = node.get("companyName");
        String companyName = null;
        if(companyNameNode != null && companyNameNode.isTextual()){
            companyName = companyNameNode.asText();
        }
        return companyName;
    }

    private static List<ContactPhone> transformPhone(JsonNode node){
        List<ContactPhone> phones = new ArrayList<>();
        for( Pair<String, ContactPhone.Type> pair : PHONE_PAIRS ){
            ContactPhone newPhone = transform(node, pair.first, pair.second);
            if( newPhone != null ){
                phones.add(newPhone);
            }
        }
        return phones;
    }

    private static ContactPhone transform(JsonNode node, String key, ContactPhone.Type type){
        if( node.has(key) ){
            return new ContactPhone(type, node.get(key).asText());
        }
        return null;
    }


    private static ContactAddress transformAddress(JsonNode node){
        return new ContactAddress(
                node.get("street").asText(),
                node.get("city").asText(),
                node.get("state").asText(),
                node.get("country").asText(),
                node.get("zipCode").asText());
    }

    private static ContactImageURL transformURi(JsonNode node) {
        return new ContactImageURL(Uri.parse(node.get("smallImageURL").asText()),
                Uri.parse(node.get("largeImageURL").asText()));
    }
}
