ALTER TABLE visits ADD visitor_id BIGINT NULL;

ALTER TABLE visits ADD CONSTRAINT FK_VISITS_ON_VISITOR FOREIGN KEY (visitor_id) REFERENCES visitors (id);

ALTER TABLE visits DROP FOREIGN KEY FK_VISITS_ON_VISITORS;

ALTER TABLE visits DROP COLUMN visitors_id;