-- 확장 기능을 추가합니다 (UUID 생성에 필요한 기능)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 기존 테이블이 있다면 삭제하고 새로 생성합니다
DROP TABLE IF EXISTS p_hubs;

-- p_hubs 테이블 생성
CREATE TABLE IF NOT EXISTS p_hubs (
                                      hub_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),      -- 허브 ID
                                      name VARCHAR(255) NOT NULL,                            -- 허브 이름
                                      address VARCHAR(255) NOT NULL,                         -- 허브 주소
                                      latitude NUMERIC(11, 8) NOT NULL,                      -- 위도
                                      longitude NUMERIC(12, 8) NOT NULL,                     -- 경도
                                      hub_sequence INTEGER NOT NULL,                          -- 허브 순서
                                      created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,  -- 생성 일시
                                      created_by VARCHAR(255),                               -- 생성자
                                      updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,  -- 수정 일시
                                      updated_by VARCHAR(255),                               -- 수정자
                                      deleted_at TIMESTAMP WITH TIME ZONE,                    -- 삭제 일시
                                      deleted_by VARCHAR(255),                               -- 삭제자
                                      is_deleted BOOLEAN DEFAULT FALSE                        -- 삭제 여부
);

-- 샘플 데이터 삽입
INSERT INTO p_hubs (name, address, latitude, longitude, hub_sequence) VALUES
                                                                          ('서울특별시 센터', '서울특별시 송파구 송파대로 55', 37.51457500, 127.10561700, 17),
                                                                          ('경기 북부 센터', '경기도 고양시 덕양구 권율대로 570', 37.65835500, 126.83167600, 16),
                                                                          ('경기 남부 센터', '경기도 이천시 덕평로 257-21', 37.20915300, 127.48667800, 15),
                                                                          ('부산광역시 센터', '부산 동구 중앙대로 206', 35.13417500, 129.05606000, 14),
                                                                          ('대구광역시 센터', '대구 북구 태평로 161', 35.88361600, 128.58297900, 13),
                                                                          ('인천광역시 센터', '인천 남동구 정각로 29', 37.44733600, 126.70762900, 12),
                                                                          ('광주광역시 센터', '광주 서구 내방로 111', 35.15513700, 126.87703800, 11),
                                                                          ('대전광역시 센터', '대전 서구 둔산로 100', 36.35170300, 127.37898500, 10),
                                                                          ('울산광역시 센터', '울산 남구 중앙로 201', 35.53837700, 129.31117900, 9),
                                                                          ('세종특별자치시 센터', '세종특별자치시 한누리대로 2130', 36.48035100, 127.28999100, 8),
                                                                          ('강원특별자치도 센터', '강원특별자치도 춘천시 중앙로 1', 37.88131500, 127.72978800, 7),
                                                                          ('충청북도 센터', '충북 청주시 상당구 상당로 82', 36.63691700, 127.49115200, 6),
                                                                          ('충청남도 센터', '충남 홍성군 홍북읍 충남대로 21', 36.60183900, 126.65997800, 5),
                                                                          ('전북특별자치도 센터', '전북특별자치도 전주시 완산구 효자로 225', 35.82127900, 127.14749200, 4),
                                                                          ('전라남도 센터', '전남 무안군 삼향읍 오룡길 1', 34.99360800, 126.48527400, 3),
                                                                          ('경상북도 센터', '경북 안동시 풍천면 도청대로 455', 36.56832000, 128.72438700, 2),
                                                                          ('경상남도 센터', '경남 창원시 의창구 중앙대로 300', 35.22681000, 128.68192700, 1);
