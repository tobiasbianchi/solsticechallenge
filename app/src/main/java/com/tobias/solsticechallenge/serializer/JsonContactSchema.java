package com.tobias.solsticechallenge.serializer;

public class JsonContactSchema {
    /**Schema that validates a Json with an array of contacts*/
    public static final String CONTACT_SCHEMA = "{" +
            "  \"$id\": \"http://example.com/root.json\"," +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\"," +
            "  \"type\": \"array\"," +
            "  \"items\": { \"$ref\": \"#/definitions/contact\" }," +
            "  \"definitions\": { " +
            "       \"contact\": {" +
            "       \"type\": \"object\"," +
            "       \"required\": [" +
            "           \"name\"," +
            "           \"id\"," +
            "           \"isFavorite\"," +
            "           \"smallImageURL\"," +
            "           \"largeImageURL\"," +
            "           \"emailAddress\"," +
            "           \"birthdate\"," +
            "           \"phone\"," +
            "           \"address\"" +
            "       ]," +
            "      \"properties\": {" +
            "           \"name\": {" +
            "               \"type\": \"string\"" +
            "           }," +
            "           \"id\": {" +
            "               \"type\": \"string\"" +
            "           }," +
            "           \"companyName\": {" +
            "               \"type\": [\"string\",\"null\"]"+
            "           }," +
            "           \"isFavorite\": {" +
            "               \"type\": \"boolean\"" +
            "           }," +
            "           \"smallImageURL\": {" +
            "               \"type\": \"string\"" +
            "           }," +
            "           \"largeImageURL\": {" +
            "               \"type\": \"string\"" +
            "           }," +
            "           \"emailAddress\": {" +
            "               \"type\": \"string\"" +
            "           }," +
            "           \"birthdate\": {" +
            "               \"type\": \"string\"" +
            "           }," +
            "           \"phone\": {" +
            "               \"type\": \"object\"," +
            "               \"properties\": {" +
            "                   \"work\": {" +
            "                       \"type\": \"string\"" +
            "                   }," +
            "                   \"home\": {" +
            "                       \"type\": \"string\"" +
            "                   }," +
            "                   \"mobile\": {" +
            "                       \"type\": \"string\"" +
            "                   }" +
            "               }" +
            "           }," +
            "           \"address\": {" +
            "               \"type\": \"object\"," +
            "               \"required\": [" +
            "                   \"street\"," +
            "                   \"city\"," +
            "                   \"state\"," +
            "                   \"country\"," +
            "                   \"zipCode\"" +
            "       ]," +
            "               \"properties\": {" +
            "                   \"street\": {" +
            "                       \"type\": \"string\"" +
            "                   }," +
            "                   \"city\": {" +
            "                       \"type\": \"string\"" +
            "                   }," +
            "                   \"state\": {" +
            "                       \"type\": \"string\"" +
            "                   }," +
            "                   \"country\": {" +
            "                       \"type\": \"string\"" +
            "                   }," +
            "                   \"zipCode\": {" +
            "                       \"type\": \"string\"" +
            "                   }" +
            "               }" +
            "           }" +
            "       }" +
            "   }" +
            " }}";
}
