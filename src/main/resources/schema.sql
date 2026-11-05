CREATE TABLE IF NOT EXISTS companies (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
                                         address VARCHAR(500),
                                         website VARCHAR(255),
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS jobs (
                                    id SERIAL PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
                                    description TEXT,
                                    salary_min DECIMAL(15, 2),
                                    salary_max DECIMAL(15, 2),
                                    location VARCHAR(255) NOT NULL,
                                    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
                                    company_id BIGINT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    CONSTRAINT fk_jobs_company FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS applications (
                                            id SERIAL PRIMARY KEY,
                                            job_id BIGINT NOT NULL,
                                            full_name VARCHAR(255) NOT NULL,
                                            email VARCHAR(255) NOT NULL,
                                            cv_url VARCHAR(500) NOT NULL,
                                            applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            CONSTRAINT fk_applications_job FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE,
                                            CONSTRAINT uq_job_email UNIQUE (job_id, email)
);

CREATE INDEX idx_jobs_location ON jobs(location);
CREATE INDEX idx_jobs_status ON jobs(status);
CREATE INDEX idx_applications_job_id ON applications(job_id);

CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_companies_updated_at
    BEFORE UPDATE ON companies
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_jobs_updated_at
    BEFORE UPDATE ON jobs
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();