package com.app.phonecontacts.model.dto.contact;

import com.app.phonecontacts.model.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    @Mapping(target = "emails", source = "emails", qualifiedByName = "stringToList")
    @Mapping(target = "numbers", source = "numbers", qualifiedByName = "stringToList")
    ContactResponse contactToContactResponse(Contact task);

    @Mapping(target = "emails", source = "emails", qualifiedByName = "listToString")
    @Mapping(target = "numbers", source = "numbers", qualifiedByName = "listToString")
    Contact contactRequestToContact(ContactRequest taskRequest);

    @Named("listToString")
    default String mapListToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return String.join(", ", list);
    }

    @Named("stringToList")
    default List<String> mapStringToList(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        return Stream.of(line.split(",")).map(String::trim).toList();
    }
}
