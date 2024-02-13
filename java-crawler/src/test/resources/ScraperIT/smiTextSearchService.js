const smiTextArea = document.createElement('textarea');

const smiTextSearchService = {

    parseAllTexts: function () {
        const that = this,
            allNodes = this.findAllTextNodes();
        const result = [];
        allNodes.forEach(function (nodeData) {
            const nodeName = nodeData.parentNode.nodeName.toLowerCase();
            const textElementProperties = {
                nodeName: nodeName,
                text: nodeData.textNode.nodeValue.trim(),
                url: nodeName === "a" ? nodeData.parentNode.href : null,
                y: nodeData.parentNode.offsetTop, //node.getBoundingClientRect();
                x: nodeData.parentNode.offsetLeft,
                width: nodeData.parentNode.offsetWidth,
                height: nodeData.parentNode.offsetHeight,
                //----
                color: getComputedStyle(nodeData.parentNode).color,
                fontSize: getComputedStyle(nodeData.parentNode).fontSize,
                fontStyle: getComputedStyle(nodeData.parentNode).fontStyle,
                bold: getComputedStyle(nodeData.parentNode).fontWeight === 'bold',
                italic: getComputedStyle(nodeData.parentNode).fontStyle === 'italic',
                backgroundColor: getComputedStyle(nodeData.parentNode).backgroundColor
            };
            result.push(textElementProperties);
        });
        return result;
    },

    findAllTextNodes: function (node) {
        const textNodes = [], that = this;

        function find(node) {
            if (that.isTextNode(node) && that.isAcceptableNode(node)) {
                textNodes.push({textNode: node, parentNode: node.parentNode});
            } else {
                for (const childNode of node.childNodes) {
                    find(childNode);
                }
            }
        }

        find(node || document.documentElement);
        return textNodes;
    },

    isTextNode: function (node) {
        return node.nodeType === Node.TEXT_NODE;
    },

    isAcceptableNode: function (textNode) {
        const nodeName = textNode.parentNode.nodeName.toLowerCase();
        return nodeName !== "script" && nodeName !== "head" && nodeName !== "script";
    }
};

smiTextArea.id = "smiTextArea";
const jsonString = JSON.stringify(smiTextSearchService.parseAllTexts());
smiTextArea.value = jsonString;
console.log("String: ", smiTextArea.value);
document.body.appendChild(smiTextArea);
