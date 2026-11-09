# Week 5 - Advanced Swing Components

| Folder | What it contains | Main class |
|---|---|---|
| activity1-jlist-renderer | JList with a custom renderer (status dots) | ContactListDemo |
| activity2-jtable | Editable JTable, custom GPA colours, add/delete | StudentTableDemo |
| activity3-jtree-tabs | JTree + JTabbedPane + JSplitPane | FileExplorerDemo |
| activity4-menus-dialogs | Mini text editor: menus, toolbar, JFileChooser | MiniEditor |
| activity5-capstone-file-manager | File manager: tree + table + menu + toolbar | FileManager |

## Run (PowerShell, JDK 11+)
    cd activity1-jlist-renderer
    javac -encoding UTF-8 ContactListDemo.java
    java ContactListDemo

Or from this folder:  `.\run.ps1 activity5-capstone-file-manager FileManager`
Check everything compiles:  `.\compile-all.ps1`

## Fixes made to the guide's code
- **MiniEditor:** the guide uses `saveAsAction` inside `saveAction` before it is
  declared, which does not compile. `saveAsAction` is now declared first, and
  it adds `.txt` if you forget the extension.
- **FileManager:** the guide scans your whole home folder before the window
  opens, which can freeze on a large drive. The tree now loads folders lazily
  (only when you expand them), shows folder names instead of full paths, and
  sorts folders first.
- **StudentTableDemo:** deleting uses `convertRowIndexToModel`, so it stays
  correct if you sort the table.
- Plain `-` replaces the em dash in titles to avoid Windows encoding problems.
