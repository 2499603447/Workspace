from datetime import datetime

# Configuration file for the Sphinx documentation builder.
#
# For the full list of built-in configuration values, see the documentation:
# https://www.sphinx-doc.org/en/master/usage/configuration.html

# -- Project information -----------------------------------------------------
# https://www.sphinx-doc.org/en/master/usage/configuration.html#project-information

project = 'ChaseDreamHouse'
currentYear = datetime.now().year
author = 'Zhang Dezhou'
copyright = '2021-' + str(currentYear) + ', ' + author
release = '1.0'

# -- General configuration ---------------------------------------------------
# https://www.sphinx-doc.org/en/master/usage/configuration.html#general-configuration

extensions = [
    'recommonmark',
    # Auto-generate section labels.
    'sphinx.ext.autosectionlabel',
    'sphinx_markdown_tables',
]

autosectionlabel_prefix_document = True

source_suffix = {
    '.rst': 'restructuredtext',
    '.md': 'markdown',
    '.txt': 'markdown',
}

templates_path = ['_templates']
exclude_patterns = []

language = 'zh_CN'

master_doc = 'index'

# -- Options for HTML output -------------------------------------------------
# https://www.sphinx-doc.org/en/master/usage/configuration.html#options-for-html-output

html_theme = 'furo'
html_title = 'ChaseDreamHouse'

#html_theme_path = [sphinx_bernard_theme.get_html_theme_path()]
#html_context = [sphinx_bernard_theme.get_html_context()]
# Add any paths that contain custom static files (such as style sheets) here,
# relative to this directory. They are copied after the builtin static files,
# so a file named "default.css" will overwrite the builtin "default.css".
html_static_path = ['_static']