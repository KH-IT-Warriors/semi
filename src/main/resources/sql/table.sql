CREATE TABLE USER_ACCOUNTS_TEST (
    ID BIGINT AUTO_INCREMENT,
    STATUS_ID BIGINT,
    ROLE_ID BIGINT,
    USER_NAME VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE USER_INFORMATIONS_TEST (
    ACCOUNT_ID BIGINT,
    NAME VARCHAR(255) NOT NULL,
    PHONE_NUMBER VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL,
    REGISTERED_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    RECENT_CONNECTION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    USER_GRADE_ID BIGINT DEFAULT 1
);

CREATE TABLE PASSWORDS_TEST(
    ACCOUNT_ID BIGINT,
    PASSWORD CHAR(255)
);