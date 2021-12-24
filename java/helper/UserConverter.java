package helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import model.User;

import javax.persistence.AttributeConverter;

public class UserConverter implements AttributeConverter<User, String> {

    static ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(User user) {
        String str = objectMapper.writeValueAsString(user);

        return str;
    }

    @SneakyThrows
    @Override
    public User convertToEntityAttribute(String s) {
        User user = objectMapper.readValue(s, User.class);
        return user;
    }
}
