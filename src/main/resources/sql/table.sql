CREATE TABLE user_account_test (
    'id' BIGINT AUTO_INCREMENT,
    'status_id' BIGINT DEFAULT 1,
    'role_id' BIGINT DEFAULT 1,
    'user_name' VARCHAR(255) NOT NULL,
    PRIMARY KEY ('id')
);

CREATE TABLE user_profiles_test (
    'account_id' BIGINT,
    'name' VARCHAR(255) NOT NULL,
    'phone_number' VARCHAR(255) NOT NULL UNIQUE,
    'email' VARCHAR(255) NOT NULL,
    'registered_time' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    'recent_connection' TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    'bonus_point' BIGINT NOT NULL DEFAULT 0,
    'user_grade_id' BIGINT DEFAULT 1
);

CREATE TABLE passwords_test(
    'account_id' BIGINT,
    'password' CHAR(255)
);

CREATE TABLE user_grades_test(
    'id' BIGINT AUTO_INCREMENT,
    'grade_name' VARCHAR(255) NOT NULL UNIQUE,
    'accumulate_rate' DECIMAL(4,3) NOT NULL,
    'achievement_condition' BIGINT NOT NULL,
    PRIMARY KEY ('id')
);

CREATE TABLE roles_test(
    'id' BIGINT AUTO_INCREMENT,
    'role_name' VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY ('id')
);

CREATE TABLE grants_test(
    'id' BIGINT AUTO_INCREMENT,
    'grant_name' VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY ('id')
);

CREATE TABLE roles_grants_test(
    'role_id' BIGINT,
    'grant_id' BIGINT
);

CREATE TABLE statuses_test(
    'id' BIGINT AUTO_INCREMENT,
    'status_name' VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY ('id')
);