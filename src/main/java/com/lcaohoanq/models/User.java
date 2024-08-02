package com.lcaohoanq.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lcaohoanq.enums.UserGenderEnum;
import com.lcaohoanq.enums.UserRoleEnum;
import com.lcaohoanq.enums.UserStatusEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("password")
    private String password;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("address")
    private String address;

    @JsonProperty("gender")
    private UserGenderEnum gender;

    @JsonProperty("role")
    private UserRoleEnum role;

    @JsonProperty("status")
    private UserStatusEnum status;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("avatar_url")
    private byte[] avatar_url;

    @JsonProperty("subscription")
    private int subscription;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
