import sphinx_material
from datetime import datetime

# Configuration file for the Sphinx documentation builder.
#
# This file only contains a selection of the most common options. For a full
# list see the documentation:
# https://www.sphinx-doc.org/en/master/usage/configuration.html

# -- Path setup --------------------------------------------------------------

# If extensions (or modules to document with autodoc) are in another directory,
# add these directories to sys.path here. If the directory is relative to the
# documentation root, use os.path.abspath to make it absolute, like shown here.
#
# import os
# import sys
# sys.path.insert(0, os.path.abspath('.'))


# -- Project information -----------------------------------------------------

project = 'ChaseDreamHouse'
currentYear = datetime.now().year
author = 'Zhang Dezhou'
copyright = '2021-' + str(currentYear) + ', ' + author

# The full version, including alpha/beta/rc tags
release = '1.0'


# -- General configuration ---------------------------------------------------

# Add any Sphinx extension module names here, as strings. They can be
# extensions coming with Sphinx (named 'sphinx.ext.*') or your custom
# ones.
extensions = [
    'recommonmark',
    'sphinx_markdown_tables',
    # Auto-generate section labels.
    'sphinx.ext.autosectionlabel',
    'sphinx_material'
]

autosectionlabel_prefix_document = True

source_suffix = {
    '.rst': 'restructuredtext',
    '.md': 'markdown',
    '.txt': 'markdown',
}

# Add any paths that contain templates here, relative to this directory.
templates_path = ['_templates']

# The language for content autogenerated by Sphinx. Refer to documentation
# for a list of supported languages.
#
# This is also used if you do content translation via gettext catalogs.
# Usually you set "language" from the command line for these cases.
language = 'zh_CN'

# List of patterns, relative to source directory, that match files and
# directories to ignore when looking for source files.
# This pattern also affects html_static_path and html_extra_path.
exclude_patterns = []

master_doc = 'index'

# -- Options for HTML output -------------------------------------------------

# The theme to use for HTML and HTML Help pages.  See the documentation for
# a list of builtin themes.
#
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