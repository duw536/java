-- 데이터베이스 생성 및 사용 (없을 경우)
CREATE DATABASE IF NOT EXISTS research_db;
USE research_db;

-- 1. 계정 (Account) : 사용자 정보 및 권한 관리
CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- 고유 번호 (PK)
    username VARCHAR(50) NOT NULL UNIQUE,     -- 아이디 (중복 불가)
    password VARCHAR(255) NOT NULL,           -- 비밀번호 (실무에선 암호화 필요)
    name VARCHAR(50) NOT NULL,                -- 사용자 실명
    
    -- ★ 권한 구분 (어드민, 업로더, 게스트)
    -- ENUM은 지정된 문자열만 들어갈 수 있도록 제한합니다.
    role ENUM('ADMIN', 'UPLOADER', 'GUEST') NOT NULL DEFAULT 'GUEST', 
    
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP -- 가입일 (자동 생성)
);

-- 2. 카테고리 (Category) : 프로젝트의 큰 분류 (예: 게임, 과제, 연구)
CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- 카테고리 고유 번호 (PK)
    name VARCHAR(50) NOT NULL UNIQUE          -- 카테고리명 (중복 불가)
);

-- 3. 프로젝트 (Project) : 연구/과제 단위 (부모 테이블)
CREATE TABLE project (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- 프로젝트 고유 번호 (PK)
    category_id INT,                          -- 카테고리 테이블 참조 (FK)
    project_name VARCHAR(100) NOT NULL UNIQUE,-- 프로젝트 이름 (중복 불가)
    description TEXT,                         -- 프로젝트 상세 설명
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 생성일
    
    -- 카테고리가 삭제되면 프로젝트의 카테고리 값은 NULL로 변경 (프로젝트는 유지)
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
);

-- 4. 자료 (Material) : 실제 CSV 파일 및 샘플 정보 (자식 테이블)
CREATE TABLE material (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- 자료 고유 번호 (PK)
    project_id INT NOT NULL,                  -- 소속 프로젝트 ID (FK)
    account_id INT NOT NULL,                  -- 업로더 계정 ID (FK)
    
    sample_name VARCHAR(100) NOT NULL,        -- 샘플 구분명 (예: 실험군 A, 테스트 1)
    
    csv_file_name VARCHAR(255) NOT NULL,      -- CSV 원본 파일명
    csv_file_path VARCHAR(500) NOT NULL,      -- CSV 다운로드 경로
    
    pptx_file_name VARCHAR(255),              -- PPTX 파일명 (없을 수 있음, NULL 허용)
    pptx_file_path VARCHAR(500),              -- PPTX 다운로드 경로 (없을 수 있음)
    
    upload_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- 업로드 일시
    
    -- 프로젝트가 삭제되면 소속된 자료들도 자동으로 삭제 (CASCADE)
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    -- 업로더 정보 연결
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- 5. 피드백 (Comment) : 자료에 대한 댓글 및 의견
CREATE TABLE comment (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- 댓글 고유 번호 (PK)
    material_id INT NOT NULL,                 -- 어떤 자료에 달린 댓글인지 (FK)
    account_id INT NOT NULL,                  -- 작성자 ID (FK)
    content TEXT NOT NULL,                    -- 댓글 내용
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 작성 일시
    
    -- 자료가 삭제되면 해당 자료의 댓글도 자동 삭제 (CASCADE)
    FOREIGN KEY (material_id) REFERENCES material(id) ON DELETE CASCADE,
    -- 작성자 정보 연결
    FOREIGN KEY (account_id) REFERENCES account(id)
);

-- [참고] 기초 데이터 넣기 (테스트용)
-- 관리자 계정 생성
INSERT INTO account (username, password, name, role) 
VALUES ('admin', '1234', '관리자', 'ADMIN');

-- 업로더 계정 생성
INSERT INTO account (username, password, name, role) 
VALUES ('student1', '1234', '김철수', 'UPLOADER');

-- 게스트(공용) 계정 생성 (이거 하나만 있으면 됩니다)
INSERT INTO account (username, password, name, role) 
VALUES ('guest', '1234', '방문자', 'GUEST');
