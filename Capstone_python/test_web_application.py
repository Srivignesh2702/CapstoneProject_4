import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture(scope="module")
def browser():
    # Initialize Chrome WebDriver
    driver = webdriver.Chrome()
    yield driver
    # Teardown - close the browser
    driver.quit()

def test_verify_title(browser):
    # Open the URL
    browser.get("http://the-internet.herokuapp.com/")
    # Verify the title of the page
    assert "The Internet" in browser.title

def test_verify_checkboxes(browser):
    # Open the Checkboxes page
    browser.get("http://the-internet.herokuapp.com/checkboxes")
    # Verify the title of the page
    checkbox_heading = browser.find_element(By.XPATH, "//h3[contains(text(),'Checkboxes')]")
    
    # Retrieve the text of the element
    checkbox_heading_text = checkbox_heading.text
    
    # Assert that the text of the element contains "Checkboxes"
    assert "Checkboxes" in checkbox_heading_text
    # Verify checkbox states
    assert not is_checkbox_checked(browser, 1)
    assert is_checkbox_checked(browser, 2)

def test_file_upload(browser):
    # Open the File Upload page
    browser.get("http://the-internet.herokuapp.com/upload")
    # Verify the title of the page
    checkbox_heading = browser.find_element(By.XPATH, "//h3[contains(text(),'File Uploader')]")
    
    # Retrieve the text of the element
    checkbox_heading_text = checkbox_heading.text
    
    # Assert that the text of the element contains "Checkboxes"
    assert "File Uploader" in checkbox_heading_text
    # Upload a file
    script_path = __file__
    script_dir = script_path.rsplit("\\", 1)[0]  # Get the directory containing the script
    file_path = script_dir + "\\test_upload.txt"
    choose_file_input = browser.find_element(By.ID, "file-upload")
    choose_file_input.send_keys(file_path)
    upload_button = browser.find_element(By.ID, "file-submit")
    upload_button.click()

def is_checkbox_checked(browser, index):
    checkbox = browser.find_element(By.XPATH, f"//input[@type='checkbox'][{index}]")
    return checkbox.is_selected()
