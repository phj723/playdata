drop table cookies;
create table cookies(
    name       VARCHAR2(50)    NOT NULL, 
    type       VARCHAR2(20)    NOT NULL, 
    position   VARCHAR2(20)    NOT NULL,
    grade      VARCHAR2(20)    NOT NULL, 
    cmt        VARCHAR2(1000)  NULL, 
    fname      VARCHAR2(100)   DEFAULT 'cookie.jpg', 
    skillname  VARCHAR2(50)    NOT NULL, 
    skillcmt   VARCHAR2(500)   NULL, 
    sfname     VARCHAR2(100)   DEFAULT 'skill.jpg', 
    PRIMARY KEY (name)               
); 

INSERT INTO cookies(name, type, position, grade, cmt, skillname, skillcmt) 
VALUES('test', '마법형', '중앙형', '레어', '씹으면 씹을수록 검게 물들어버리는 감초에 푹 절여 만든 쿠키',
'해골 부르기', '검은 번개의 환영으로 큰 피해와 함께 감초젤리 해골이 소환된다.'); 

