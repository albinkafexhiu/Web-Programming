package mk.finki.ukim.mk.lab.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname, String> {

    @Override
    public String convertToDatabaseColumn(AuthorFullname authorFullname) {
        if (authorFullname == null) {
            return null;
        }
        return authorFullname.getName() + " " + authorFullname.getSurname();
    }

    @Override
    public AuthorFullname convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().length() == 0) {
            return null;
        }
        String[] parts = dbData.split(" ");
        return new AuthorFullname(parts[0], parts[1]);
    }
}
