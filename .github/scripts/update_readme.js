const fs = require('fs');
const path = require('path');

const repoDir = process.cwd();
const problems = [];
const items = fs.readdirSync(repoDir);

for (const item of items) {
    const itemPath = path.join(repoDir, item);
    
    // Ignore files and dotfiles/dotfolders
    if (item.startsWith('.') || !fs.statSync(itemPath).isDirectory()) continue;
    
    const readmePath = path.join(itemPath, 'README.md');
    if (fs.existsSync(readmePath)) {
        const content = fs.readFileSync(readmePath, 'utf8');
        
        // Extract Title and Difficulty
        const titleMatch = content.match(/<h2><a href=[^>]+>(.*?)<\/a><\/h2>/);
        const diffMatch = content.match(/alt='Difficulty: (.*?)'/);
        
        const title = titleMatch ? titleMatch[1] : item;
        const difficulty = diffMatch ? diffMatch[1] : 'Unknown';
        
        // Find Solution file
        let solutionFile = '';
        const files = fs.readdirSync(itemPath);
        for (const file of files) {
            if (file !== 'README.md') {
                solutionFile = file;
                break;
            }
        }
        
        // Number for sorting
        const numPart = item.split('-')[0];
        const num = !isNaN(numPart) ? parseInt(numPart) : 9999;
        
        problems.push({ folder: item, title, difficulty, solution: solutionFile, number: num });
    }
}

problems.sort((a, b) => a.number - b.number);

let tableContent = "| Problem | Difficulty | Solution |\n| :--- | :---: | :--- |\n";
for (const p of problems) {
    let diffColor = "red";
    if (p.difficulty === 'Easy') diffColor = "brightgreen";
    else if (p.difficulty === 'Medium') diffColor = "orange";
    
    const diffBadge = "<img src='https://img.shields.io/badge/" + p.difficulty + "-" + diffColor + "?style=flat-square' alt='" + p.difficulty + "'>";
    const solLink = p.solution ? "[" + p.solution + "](./" + p.folder + "/" + p.solution + ")" : "No Solution";
    
    tableContent += "| [" + p.title + "](./" + p.folder + ") | " + diffBadge + " | " + solLink + " |\n";
}

// Replace the table in README
const readmePath = path.join(repoDir, 'README.md');
let readmeContent = fs.readFileSync(readmePath, 'utf8');

const regex = /(<!-- PROBLEMS-TABLE-START -->)[\s\S]*?(<!-- PROBLEMS-TABLE-END -->)/;
if (regex.test(readmeContent)) {
    readmeContent = readmeContent.replace(regex, `$1\n${tableContent}$2`);
    fs.writeFileSync(readmePath, readmeContent, 'utf8');
    console.log(`Updated README with ${problems.length} problems!`);
} else {
    console.error("Could not find the PROBLEMS-TABLE tags in README.md");
    process.exit(1);
}
