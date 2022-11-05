import sphinx_material
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
    'sphinx_material'
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

html_theme_options = {
    # 'base_url': base_url,
    'color_primary': 'light-green',
    'color_accent': 'orange',
    'logo_icon': '&#xe150',
    'master_doc': False,

    # Set you GA account ID to enable tracking
    # 'google_analytics_account': '142118122',
    
    # Set the repo location to get a badge with stats
    'repo_url': 'https://github.com/yikeke/zh-style-guide/',
    'repo_name': 'zh-style-guide',

    # Visible levels of the global TOC; -1 means unlimited
    'globaltoc_depth': 2,
    # If False, expand all TOC entries
    'globaltoc_collapse': False,
    # If True, show hidden TOC entries
    'globaltoc_includehidden': False,

    # 'heroes': {'index': 'An open-source style guide for writing Chinese technical documents',
    #            '文档结构样式/index': 'Structure, Focus, Unity and Flow','语言风格/index': 'Stay Close to Your Users','文档内容元素/index': 'Details Matter','标点符号/index': 'Details Matter'},
}

html_theme_path = sphinx_material.html_theme_path()
html_context = sphinx_material.get_html_context()
# Add any paths that contain custom static files (such as style sheets) here,
# relative to this directory. They are copied after the builtin static files,
# so a file named "default.css" will overwrite the builtin "default.css".
html_static_path = ['_static']
# Use custom CSS configs
templates_path = ['_templates']